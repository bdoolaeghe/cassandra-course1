TP2 - Practice the java Datastax driver
=======================================
Clone the java Maven project [cassandra-course1](http://gitlab.soat.fr/bruno.doolaeghe/cassandra-course1), and import it in your favorite IDE. You can see the project is partly implemented...

Now, write in _TemperatureByCityRepository_ the following methods, with a unit test in in _TemperatureByCityRepositoryTest_, :
* [Q2.1] _getById(city, date)_, returning the temperature in a city at a given date (hint: use the _Mapper<TemperatureByCity>_)
* [Q2.2] _getByIdAsync(city, date)_, same -- but asynchronous -- mehtod as _getById(city, date)_  (hint: also use the Mapper<TemperatureByCity>)
* [Q2.3] _getLastByCity(city)_, giving the last temperature in a given city (hint: use _TemperatureByCityAccessor_)
* [Q2.4] _getLastByCityAsync(city)_, same -- but asynchronous -- method as _getLastByCity(city)_  (hint: use same accessor)
* [Q2.5] _getFirstByCity(city)_, giving the oldest temperature in a given city (hint: use the accessor)
* [Q2.6] _saveBatch(temperatures)_, saving a list of temperatures at some dates in some cities (hint: this "batch" can be splited in paralllel executions)


Bonus
-----
* [Q2.7] _getByCityUntil(city, boudnDate)_, returning all the temperatures in a given city before a given bound date, in reverse chronological order (that is, the latest temperature first)
* [Q2.8] _getByCityUntilAsc(city, boudnDate)_, same as _getByCityUntil(city, boudnDate)_ but in chronological order (that is, the latest temperature in last)

Super bonus
-----------
* [Q2.9] write in _TemperatureByDateRepository_ new repository the method _getByDate(date)_, to get the temperatures in every city at a given date (hint: use table temperature_by_date, with [create_table_temperature_by_date.cql](http://gitlab.soat.fr/bruno.doolaeghe/cassandra-course1/blob/master/src/main/resources/cql/create_table_temperature_by_date.cql) and [insert_data_temperature_by_date.cql](insert_data_temperature_by_date.cql) )
* [Q2.10] create an aggregating service _TemperatureService_, to:
 * save a temperature in a city at a given date (hint: delegate to the 2 underlying repositories _TemperatureByCityRepository_ and __TemperatureByDateRepository_)
 * get the temperatures in a city at a given date
 * get the temperatures everywhere at a given date
