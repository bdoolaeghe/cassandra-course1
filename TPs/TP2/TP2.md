TP2 - Practice the java Datastax driver
=======================================
Clone the java project [cassandra-course1](http://gitlab.soat.fr/bruno.doolaeghe/cassandra-course1). You can see the project is partly implemented...

Now, write the following methods, with a unit test:
* [Q2.1] in _TemperatureByCityRepository_, write the method _getById(city, date)_, returning the temperature in a city at a given date (hint: use the Mapper<TemperatureByCity>)
* [Q2.2] same as Q2.1, but  get temperature in a city at a given date [async mapper]
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

