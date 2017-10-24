TP1 - solutions
===============
* **[Q1.0]**

```
 select * from temperature_by_city where city = 'paris';
```
* **[Q1.1]**

```
 select * from temperature_by_city where city = 'paris' and date = '2017-01-02';
```
* **[Q1.2]**

```
 select * from temperature_by_city where city = 'paris'  limit 1;
```
* **[Q1.3]**

```
 select * from temperature_by_city where city = 'berlin' and date <= '2017-01-03' and date >= '2017-01-01';
```
* **[Q1.4]**

```
 select * from temperature_by_city where city = 'berlin'  and date <= '2017-01-03' and date >= '2017-01-01' order by date desc;
```
* **[Q1.5]** 
** solution 1:

```
CREATE INDEX idx_temperature_by_date ON temperature_by_city (date) ; 
SELECT * FROM temperature_by_city WHERE date = '2017-01-01'
```
** solution 2:

```
SELECT * FROM temperature_by_date WHERE date = '2017-01-01'
```

