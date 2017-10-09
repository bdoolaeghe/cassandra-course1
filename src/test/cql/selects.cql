use my_keyspace;

// la temperature à paris par ordre chronoloqiue inverse
select * from temperature_by_city
where city = 'paris';

// la dernière température à paris
select * from temperature_by_city
where city = 'paris'
limit 1;

// la temperatures à paris jusqu'au 3 janvier 2017', par ordre chronoloqiue inverse
select * from temperature_by_city
where city = 'paris'
and probe_date <= '2017-01-03';

// la temperatures à paris jusqu'au 3 janvier 2017', par ordre chronoloqiue
select * from temperature_by_city
where city = 'paris'
and probe_date <= '2017-01-03'
order by probe_date asc;

// les temperatures dans le monde au 1er janvier 1970
SELECT * FROM temperature_by_probe_date
where probe_date = '2017-01-01';
