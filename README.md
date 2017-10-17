Training
========
CQL
---
write the following CQL queries:
* get temperature in a city at a given date [base query]
* get the last temperature in a given city [limit]
* get the temperatures in every city at a given date [second table]
* get all the temperatures in chronological order in a given city before a given date [<=]
* get all the temperatures in reverse-chronological order in a given city before a given date [order by]


Java Datastax driver
--------------------
Write the following services, with a unit test:
Q21. get temperature in a city at a given date [mapper]
Q22. asynchronously get temperature in a city at a given date [async mapper]
Q23. get the last temperature in a given city [accessor]
Q24. asynchronously get the last temperature in a given city [async accessor]
Q25. get the first temperature in a given city [accessor]
Q26. save a "batch" of temperatures at some dates in some cities [bulk operations]
Q27. get all the temperatures in chronological order in a given city before a given date [bonus]
Q28. get all the temperatures in reverse-chronological order in a given city before a given date  
Q29. get the temperatures in every city at a given date [second table]
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

**Solution**: download and unzip [Huperic sigar|https://sourceforge.net/projects/sigar/files/sigar/1.6/hyperic-sigar-1.6.4.zip/download] and add its lib/ path as VM options of your test:
```
-Djava.library.path=<path_to_lib/_of_sigar>
```
r

Codec not found for requested operation: [date <-> java.time.LocalDate]
-----------------------------------------------------------------------
```
com.datastax.driver.core.exceptions.CodecNotFoundException: Codec not found for requested operation: [date <-> java.time.LocalDate]
```

**Solution:** [install "extras" driver codecs|http://docs.datastax.com/en/developer/java-driver/3.1/manual/custom_codecs/extras/] to map java.time.LocalDate to CQL date

How to bulk load a dataset in cassandra in a unit test ?
--------------------------------------------------------
**Solution**: use CQLDataLoader.
```
new CQLDataLoader(session).load(new ClassPathCQLDataSet("cql/insert_dataset.cql", false));
```


Links
=====
Download
--------
* [Practical work|http://gitlab.soat.fr/bruno.doolaeghe/cassandra-course1]
* [cassandra download|http://www.apache.org/dyn/closer.lua/cassandra/3.0.14/apache-cassandra-3.0.14-bin.tar.gz]
* [cassandra-unit|https://github.com/jsevellec/cassandra-unit]
* [cassandra-unit examples|https://github.com/jsevellec/cassandra-unit-examples]
Resources
---------
* [CQL3 syntax]
* [Markdown syntax|https://confluence.atlassian.com/bitbucketserver/markdown-syntax-guide-776639995.html]

