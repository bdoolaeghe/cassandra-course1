![alt text](https://cdn.pixabay.com/photo/2017/08/15/08/16/faq-2643072_640.jpg "FAQ")

Table of Contents
=================

   * [FAQ](#faq)
      * [What does the CQL clause "ALLOW FILTERING" means ?](#what-does-the-cql-clause-allow-filtering-means-)
      * [issue with no libsigar-amd64-linux.so](#issue-with-no-libsigar-amd64-linuxso)
      * [Codec not found for requested operation: [date &lt;-&gt; java.time.LocalDate]](#codec-not-found-for-requested-operation-date---javatimelocaldate)
      * [How to bulk load a dataset in cassandra in a unit test ?](#how-to-bulk-load-a-dataset-in-cassandra-in-a-unit-test-)
      * [How can I change consistency level (CL) ?](#how-can-i-change-consistency-level-cl-)

FAQ
===

What does the CQL clause "ALLOW FILTERING" means ?
--------------------------------------------------
If you try a SELECT CQL query and get the following return:
```
Bad Request: Cannot execute this query as it might involve data filtering and thus may have unpredictable performance. If you want to execute this query despite the performance unpredictability, use ALLOW FILTERING.
```
That means you are trying to SELECT a set of data which can not be directly identified by cassandra cluster. You have 3 solutions:
* add the CQL clause "ALLOW FILTERING" to your SELECT query, but this may be particulary unefficient and costfull for cassandra (such as a RDBMS full scan)
* create a secondary index (but this also implies some performance overhead for read and for write operations)
* redesign your data model and create a new table containing your data with PK matching your SELECT criteria.

See [More details](https://www.datastax.com/dev/blog/allow-filtering-explained-2)

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

