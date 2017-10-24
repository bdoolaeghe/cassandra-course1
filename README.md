Table of Contents
=================

   * [Training](#training)
      * [Practice the CQL](#practice-the-cql)
      * [Practice the java Datastax driver](#practice-the-java-datastax-driver)
   * [FAQ](#faq)
      * [issue with no libsigar-amd64-linux.so](#issue-with-no-libsigar-amd64-linuxso)
      * [Codec not found for requested operation: [date &lt;-&gt; java.time.LocalDate]](#codec-not-found-for-requested-operation-date---javatimelocaldate)
      * [How to bulk load a dataset in cassandra in a unit test ?](#how-to-bulk-load-a-dataset-in-cassandra-in-a-unit-test-)
      * [How can I change consistency level (CL) ?](#how-can-i-change-consistency-level-cl-)
   * [Links](#links)
      * [Download](#download)
      * [Resources](#resources)

Training
========
TP1 - Practice CQL
------------------
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

* create the keyspace ['my_keyspace'](http://gitlab.soat.fr/bruno.doolaeghe/cassandra-course1/blob/master/src/main/resources/cql/create_keyspace.cql):

```
cqlsh -f create_keyspace.cql
```
* create the table [temperature_by_city](http://gitlab.soat.fr/bruno.doolaeghe/cassandra-course1/blob/master/src/main/resources/cql/create_table_temperature_by_city.cql):

```
cqlsh -f create_table_temperature_by_city.cql
```
* insert a [sample dataset]()

```
cqlsh -f insert_dataset_for_temperature_by_city.cql
```
* connect to cqlsh in interactive mode:

```
cqlsh
``

**Now your turn !** Write the following CQL queries:
* **[Q1.0]** get all temperatures in paris [basic query]
* **[Q1.1]** get temperature in paris the '2017-01-02' [basic query]
* **[Q1.2]** get the last temperature in paris [LIMIT]
* **[Q1.3]** get all the temperatures in 'berlin' between the 1 and 3 january 2017 [<=]
* **[Q1.4]** get all the temperatures in 'berlin' between the 1 and 3 january 2017 in reverse chronological order [order by]
* **[Q1.5]** get the temperature in every city the '2017-01-01' [query by date]


Practice the java Datastax driver
---------------------------------
Write the following services, with a unit test:
* [Q2.1] get temperature in a city at a given date [mapper]
* [Q2.2] asynchronously get temperature in a city at a given date [async mapper]
* [Q2.3] get the last temperature in a given city [accessor]
* [Q2.4] asynchronously get the last temperature in a given city [async accessor]
* [Q2.5] get the first temperature in a given city [accessor]
* [Q2.6] save a "batch" of temperatures at some dates in some cities [bulk operations]
* [Q2.7] get all the temperatures in chronological order in a given city before a given date [bonus]
* [Q2.8] get all the temperatures in reverse-chronological order in a given city before a given date  
* [Q2.9] get the temperatures in every city at a given date [second table]
Q30. [bonus] create an aggregating service, to:
 * save a temperature in a city at a giben date
 * get the temperatures in a city at a giben date
 * get the temperatures everywhere at a given date



FAQ
===

issue with no libsigar-amd64-linux.so
-------------------------------------
Cassandra junit fails with:
```
no libsigar-amd64-linux.so in java.library.path
org.hyperic.sigar.SigarException: no libsigar-amd64-linux.so in java.library.path
	at org.hyperic.sigar.Sigar.loadLibrary(Sigar.java:172)
	at org.hyperic.sigar.Sigar.<clinit>(Sigar.java:100)
	at org.apache.cassandra.utils.SigarLibrary.<init>(SigarLibrary.java:47)
	at org.apache.cassandra.utils.SigarLibrary.<clinit>(SigarLibrary.java:28)
	at org.apache.cassandra.service.StartupChecks$7.execute(StartupChecks.java:216)
	at org.apache.cassandra.service.StartupChecks.verify(StartupChecks.java:112)
	at org.apache.cassandra.service.CassandraDaemon.setup(CassandraDaemon.java:196)
	at org.apache.cassandra.service.CassandraDaemon.activate(CassandraDaemon.java:601)
	at org.cassandraunit.utils.EmbeddedCassandraServerHelper$1.run(EmbeddedCassandraServerHelper.java:129)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
```

**Solution**: download and unzip [Huperic sigar](https://sourceforge.net/projects/sigar/files/sigar/1.6/hyperic-sigar-1.6.4.zip/download) and add its lib/ path as VM options of your test:
```
-Djava.library.path=<path_to_lib/_of_sigar>
```

Codec not found for requested operation: [date <-> java.time.LocalDate]
-----------------------------------------------------------------------
```
com.datastax.driver.core.exceptions.CodecNotFoundException: Codec not found for requested operation: [date <-> java.time.LocalDate]
```

**Solution:** install ["extras" driver codecs](http://docs.datastax.com/en/developer/java-driver/3.1/manual/custom_codecs/extras/) to map java.time.LocalDate to CQL date

How to bulk load a dataset in cassandra in a unit test ?
--------------------------------------------------------
**Solution**: use CQLDataLoader.
```
new CQLDataLoader(session).load(new ClassPathCQLDataSet("cql/insert_dataset.cql", false));
```

How can I change consistency level (CL) ?
-----------------------------------------
__The default ([CL](https://docs.datastax.com/en/cassandra/3.0/cassandra/dml/dmlConfigConsistency.html#dmlConfigConsistency__dml-config-write-consistency)) is set to ONE.__
in a cqlsh, you can change ([CL](https://docs.datastax.com/en/cassandra/3.0/cassandra/dml/dmlConfigConsistency.html#dmlConfigConsistency)) before executing your request:
```
// set the consistency (e.g. QUORUM))
CONSISTENCY QUORUM;

// then run your query:
// ...

// At any point you can check the consistency using:
CONSISTENCY;
```

With the Datastax java driver, you can setup the CL at different levels:

when building your [Cluster](http://docs.datastax.com/en/drivers/java/2.0/com/datastax/driver/core/Cluster.html) (set a default CL):
```java
Cluster.Builder clusterBuilder = Cluster.builder()
                .withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.QUORUM));
cluster = clusterBuilder.build();
```
Using the [Mapper.option](http://docs.datastax.com/en/developer/java-driver/2.1/manual/object_mapper/using/#mapper-options), On the Mapper: 
```java
mapper.setDefaultGetOptions(Mapper.Option.consistencyLevel(ConsistencyLevel.QUORUM);
```
Or on a Mapper method invokation:
```java
mapper.save(myEntity, Mapper.Option.consistencyLevel(ConsistencyLevel.QUORUM);
```
And also on the [Accessor](http://docs.datastax.com/en/drivers/java/2.1/com/datastax/driver/mapping/annotations/Accessor.html) with [@QueryParameters](http://docs.datastax.com/en/developer/java-driver/2.1/manual/object_mapper/using/#customizing-the-statement):
```java
@Query("SELECT * FROM ks.users")
@QueryParameters(consistency="QUORUM")
public ListenableFuture<Result<User>> getAllAsync();
```

Links
=====

Download
--------
* [Practical work](http://gitlab.soat.fr/bruno.doolaeghe/cassandra-course1)
* [cassandra download](http://www.apache.org/dyn/closer.lua/cassandra/3.0.14/apache-cassandra-3.0.14-bin.tar.gz)

Resources
---------
* [CQL3 syntax](https://github.com/apache/cassandra/blob/cassandra-2.0/doc/cql3/CQL.textile)
* [Datastax drivers](http://docs.datastax.com/en/developer/driver-matrix/doc/common/driverMatrix.html)
* [Datastax java driver documentation](https://docs.datastax.com/en/developer/java-driver/3.3/)
* [cassandra-unit](https://github.com/jsevellec/cassandra-unit)
* [cassandra-unit examples](https://github.com/jsevellec/cassandra-unit-examples)

* [Markdown syntax](https://confluence.atlassian.com/bitbucketserver/markdown-syntax-guide-776639995.html)
* [MD TOC generator](https://github.com/ekalinin/github-markdown-toc)
