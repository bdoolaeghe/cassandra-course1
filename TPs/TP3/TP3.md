TP3 - FAULT TOLERANCE
=====================

Installation
************
Let's install and start a 3 node cassandra cluster (using docker-compose)...

install scope
-------------

[Weave scope](https://www.weave.works/oss/scope/) will help to manage your cassandra cluster docker containers.

* download and start weave scope:
```
curl -L git.io/scope -o ~/bin/scope &&
chmod +x ~/bin/scope &&
~/bin/scope launch
```

* [open scope](http://localhost:4040) in a web browser.

install 3 node cassandra cluster
--------------------------------

A 3 node cassandra cluster is already configured in [docker-compose.yml](docker-compose.yml)

* startup the cluster in docker:
```
cd TPS/TP3/
docker-compose -d
```

_NB: check logs with:_
```
docker-compose logs -f
```
Once the cluster is up, you should see the 3 connected containers on weave scope.


create keyspace, table, and insert data
---------------------------------------
With [scope](http://localhost:4040), open a terminal on one of the 3 cassandra node containers _(e.g. cassandra-node-0)_. The TPs sources are mounted in /TPs/.
```
cqlsh -f /TPs/TP1/create_keyspace.cql
cqlsh -f /TPs/TP1/create_table_temperature_by_city.cql
cqlsh -f /TPs/TP1/insert_dataset_for_temperature_by_city.cql
```

[Q3.1] experiment coordinator node
**********************************

Let's try to query from any node: if you open a cqlsh from any cassandra node _(cassandra-node-0, cassandra-node-1, cassandra-node-2)_, you should be able to read the same inserted data:
```
cqlsh> use my_keyspace ;
cqlsh:my_keyspace> select count(*) from temperature_by_city ;

 count
-------
    38

(1 rows)

```

Data repartition
****************

Let suppose one node has broken down...

* To simulate a crash, stop the node-2 docker (from a host terminal):
```
docker stop cassandra-node-2 
```
You can see on [scope](http://localhost:4040) the last node has disappeared.

* Open a cqlsh from cassandra-node-1 (in [scope](http://localhost:4040)) , and try to query the data from 'paris': 
```
cqlsh> use my_keyspace ;
cqlsh:my_keyspace> SELECT * from temperature_by_city where city = 'paris' ;
```
* Now,  try to request the data from 'berlin':
```
cqlsh:my_keyspace> SELECT * from temperature_by_city where city = 'berlin' ;
```

Consistency tunning
***********************
Node crash & play with CL

Repair
**********
try nodetools
