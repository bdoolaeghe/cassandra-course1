TP2 - Practice the java Datastax driver
=======================================
Clone the java Maven project [cassandra-course1/TPS/TP2](TPs/TP2), and import it in your favorite IDE. You can see the project is partly implemented...

Now, write in [TemperatureByCityRepository](TPs/TP2/src/main/java/fr/soat/cassandra/course1/repository/TemperatureByCityRepository.java) the following methods, with a unit test in in [TemperatureByCityRepositoryTest](TPs/TP2/src/test/java/fr/soat/cassandra/course1/repository/TemperatureByCityRepositoryTest.java), :
* *[Q2.1]* _getById(city, date)_, returning the temperature *in a city at a given date* (hint: use the _Mapper<TemperatureByCity>_)
* *[Q2.2]* _getByIdAsync(city, date)_, same -- *but asynchronous* -- mehtod as _getById(city, date)_  (hint: also use the Mapper<TemperatureByCity>)
* *[Q2.3]* _getLastByCity(city)_, giving the *last temperature in a given city* (hint: use _TemperatureByCityAccessor_)
* *[Q2.4]* _getLastByCityAsync(city)_, same -- *but asynchronous* -- method as _getLastByCity(city)_  (hint: use same accessor)
* *[Q2.5]* _getFirstByCity(city)_, giving the *oldest temperature in a given city* (hint: use the accessor)
* *[Q2.6]* _saveBatch(temperatures)_, *saving a list of temperatures* at some dates in some cities (hint: don't use cassandra [BatchStatement](https://docs.datastax.com/en/cql/3.3/cql/cql_reference/cqlBatch.html), but apply save operations in paralllel)


Extra
-----
* *[Q2.7]* _getByCityUntil(city, boudnDate)_, returning all the temperatures *in a given city before a given bound date, in reverse chronological order* (that is, the latest temperature first)
* *[Q2.8]* _getByCityUntilAsc(city, boudnDate)_, same as _getByCityUntil(city, boudnDate)_ but *in chronological order* (that is, the latest temperature in last)

Extra bis !
-----------
* *[Q2.9]* write in a new repository class [TemperatureByDateRepository](TPs/TP2/src/main/java/fr/soat/cassandra/course1/repository/TemperatureByDateRepository.java) the method _getByDate(date)_, to get the temperatures at a given date *in every city * (hint: use table temperature_by_date, with [create_table_temperature_by_date.cql](TPS/TP2/src/main/resources/cql/create_table_temperature_by_date.cql) and [insert_dataset_for_temperature_by_date.cql](TPS/TP2//src/main/resources/cql/insert_dataset_for_temperature_by_date.cql) )
* *[Q2.10]* create an *aggregating service* [TemperatureService](TPs/TP2/src/main/java/fr/soat/cassandra/course1/service/TemperatureService.java), to:
 * save a temperature *in a city at a given date* (hint: delegate to the 2 underlying repositories [TemperatureByCityRepository](TPs/TP2/src/main/java/fr/soat/cassandra/course1/repository/TemperatureByCityRepository.java) and [TemperatureByDateRepository](TPs/TP2/src/main/java/fr/soat/cassandra/course1/repository/TemperatureByDateRepository.java))
 * get the temperatures in a *given city*
 * get the temperatures at a *given date*

