![alt text](http://thelastpickle.com/images/monitoring-cassandra-with-intel-snap/Apache_Cassandra_ico.png "TP1")

TP1 - Practice CQL
==================
_Install Cassandra server_
* [download](http://www.apache.org/dyn/closer.lua/cassandra/3.0.14/apache-cassandra-3.0.14-bin.tar.gz) cassandra tar ball
* untar in a local directory (e.g. in ''~/dev/cassandra/apache-cassandra-3.0.14'')
* setup environment variables:

```
cd dev/cassandra/apache-cassandra-3.0.14/
export CASSANDRA_HOME=`pwd`
export PATH="$CASSANDRA_HOME/bin:$PATH"
```

* startup single cassandra node:

```
bin/cassandra
```

_Now, connect to server with **cqlsh** client, and insert some data for playing with.._

* create the keyspace ['my_keyspace'](create_keyspace.cql):

```
cqlsh -f create_keyspace.cql
```
* create the table [temperature_by_city](create_table_temperature_by_city.cql):

```
cqlsh -f create_table_temperature_by_city.cql
```
* insert a [sample dataset](insert_dataset_for_temperature_by_city.cql)

```
cqlsh -f insert_dataset_for_temperature_by_city.cql
```
* connect to cqlsh in interactive mode:

```
cqlsh
```

**Now your turn !** Write the following CQL queries:
* **[Q1.0]** get all temperatures in paris _[basic query]_
* **[Q1.1]** get temperature in paris the '2017-01-02' _[basic query]_
* **[Q1.2]** get the last temperature in paris _[LIMIT]_
* **[Q1.3]** get all the temperatures in 'berlin' between the 1 and 3 january 2017 _[slice]_
* **[Q1.4]** get all the temperatures in 'berlin' between the 1 and 3 january 2017 in reverse chronological order _[order by]_
* **[Q1.5]** get the temperature in every city the '2017-01-01' _[query by date]_


