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

* create the keyspace ['my_keyspace'](TPS/TP2/src/main/resources/cql/create_keyspace.cql):

```
cqlsh -f create_keyspace.cql
```
* create the table [temperature_by_city](TPS/TP2/src/main/resources/cql/create_table_temperature_by_city.cql):

```
cqlsh -f create_table_temperature_by_city.cql
```
* insert a [sample dataset](TPS/TP2/src/main/resources/cql/insert_dataset_for_temperature_by_city.cql)

```
cqlsh -f insert_dataset_for_temperature_by_city.cql
```
* connect to cqlsh in interactive mode:

```
cqlsh
```

**Now your turn !** Write the following CQL queries:
* **[Q1.0]** get all temperatures in paris [basic query]
* **[Q1.1]** get temperature in paris the '2017-01-02' [basic query]
* **[Q1.2]** get the last temperature in paris [LIMIT]
* **[Q1.3]** get all the temperatures in 'berlin' between the 1 and 3 january 2017 [slice]
* **[Q1.4]** get all the temperatures in 'berlin' between the 1 and 3 january 2017 in reverse chronological order [order by]
* **[Q1.5]** get the temperature in every city the '2017-01-01' [query by date]


