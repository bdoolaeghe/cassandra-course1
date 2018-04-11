TP3 - FAULT TOLERANCE
=====================

Installation
------------
Let's install and start a 3-node cassandra cluster (using docker-compose)...

### Install scope


[Weave scope](https://www.weave.works/oss/scope/) will help to manage your cassandra cluster docker containers.

* download and start weave scope:
```
curl -L git.io/scope -o ~/bin/scope &&
chmod +x ~/bin/scope &&
~/bin/scope launch
```

* [open](http://localhost:4040) scope in a web browser.

### Install a 3-node cassandra cluster

A 3-node cassandra cluster is already configured in [docker-compose.yml](docker-compose.yml)

Startup the cluster with docker-compose:
```
cd TPS/TP3/
docker-compose -d
```

_NB: you can check logs with:_
```
docker-compose logs -f
```
Once the cluster is up, you should [see the 3 connected containers on weave scope](http://localhost:4040).



### Create keyspace, table, and insert data

With [scope](http://localhost:4040), open a terminal on one of the 3 cassandra node containers _(e.g. cassandra-node-0)_. For convinience, the TPs sources are mounted in _/TPs/_ in each docker.
```
cqlsh -f /TPs/TP1/create_keyspace.cql
cqlsh -f /TPs/TP1/create_table_temperature_by_city.cql
cqlsh -f /TPs/TP1/insert_dataset_for_temperature_by_city.cql
```

TP3.1) Masterless architecture
------------------------------

Let's try to query from each node: if you open a cqlsh from any cassandra node _(cassandra-node-0, cassandra-node-1, cassandra-node-2)_, you should be able to read the same inserted data:
```
cqlsh> use my_keyspace ;
cqlsh:my_keyspace> select count(*) from temperature_by_city ;

```
*Any node can be contacted to query any data !*

TP3.2) Data availability
------------------------
### Cluster with no replication

The keyspace we created has been setup with a replication factor set to 1 (i.e. no replicatoin). 

Let's suppose one node is fallen...

* stop the node-2 docker (from a host terminal):
```
docker stop cassandra-node-2 
```
_NB: You can see on [scope](http://localhost:4040) the last node has disappeared._

* Open a cqlsh from cassandra-node-1 (in [scope](http://localhost:4040)) , and query the data from 'paris': 
```
cqlsh> use my_keyspace ;
cqlsh:my_keyspace> SELECT * from temperature_by_city where city = 'paris' ;
```
* Now,  query the data from 'berlin':
```
cqlsh:my_keyspace> SELECT * from temperature_by_city where city = 'berlin' ;
```
What's happening ?


### Cluster with replication (RF=2)
Now, let's have some data replication. 

* From any node create a new *my_keyspace_rf2* keyspace with replication (RF=2):
```
cqlsh -f /TPs/TP3/create_keyspace_rf2.cql
cqlsh -f /TPs/TP1/create_table_temperature_by_city.cql
cqlsh -f /TPs/TP1/insert_dataset_for_temperature_by_city.cql
```

* Again, shutdown a node, and query the data from 'paris' and 'berlin' as we did [with no replication](#user-content-cluster-with-no-replication). Conclusion ?


TP3.3) Tunable consistency
--------------------------
In a cqlsh, display the current consistency level (CL):
```
cqlsh:my_keyspace_rf2> CONSISTENCY;
Current consistency level is ONE.
```

Shutdown cassandra-node-2:
```
docker stop cassandra-node-2 
```

Query the number of temperatures in CL=ALL:
```
cqlsh:my_keyspace_rf2> CONSISTENCY ALL
cqlsh:my_keyspace_rf2> SELECT count(*) from temperature_by_city ;
```

Query the number of temperatures, after downgrading CL to ONE:
```
cqlsh:my_keyspace_rf2> CONSISTENCY ONE
cqlsh:my_keyspace_rf2> SELECT count(*) from temperature_by_city ;
```
