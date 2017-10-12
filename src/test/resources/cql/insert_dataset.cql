USE my_keyspace;

TRUNCATE temperature_by_city;
TRUNCATE temperature_by_probe_date;

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2016-01-01', 2.1);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-01', 'paris', 2.1);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2016-01-01', 0.1);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-01', 'londres', 0.1);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2016-01-01', -1.1);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-01', 'berlin', -1.1);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2016-01-02', 3.2);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-02', 'paris', 3.2);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2016-01-02', 1.2);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-02', 'londres', 1.2);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2016-01-02', -1.2);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-02', 'berlin', -1.2);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2016-01-03', 3.3);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-03', 'paris', 3.3);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2016-01-03', 1.3);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-03', 'londres', 1.3);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2016-01-03', -1.3);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-03', 'berlin', -1.3);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2016-01-04', 3.4);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-04', 'paris', 3.4);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2016-01-04', 1.4);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-04', 'londres', 1.4);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2016-01-05', 3.5);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-05', 'paris', 3.5);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2016-01-05', -1.5);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-05', 'berlin', -1.5);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2016-01-06', 3.6);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-06', 'paris', 3.6);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2016-01-06', 1.6);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-06', 'londres', 1.6);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2016-01-06', -1.6);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-06', 'berlin', -1.6);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2016-01-07', 3.7);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-07', 'paris', 3.7);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2016-01-07', 1.7);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-07', 'londres', 1.7);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2016-01-07', -1.7);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2016-01-07', 'berlin', -1.7);


/* 2017 */

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2017-01-01', 3);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-01', 'paris', 3);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2017-01-01', 1);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-01', 'londres', 1);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2017-01-01', -1);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-01', 'berlin', -1);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2017-01-02', 3.2);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-02', 'paris', 3.2);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2017-01-02', 1.2);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-02', 'londres', 1.2);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2017-01-02', -1.2);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-02', 'berlin', -1.2);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2017-01-03', 3.3);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-03', 'paris', 3.3);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2017-01-03', 1.3);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-03', 'londres', 1.3);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2017-01-03', -1.3);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-03', 'berlin', -1.3);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2017-01-04', 3.4);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-04', 'paris', 3.4);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2017-01-04', 1.4);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-04', 'londres', 1.4);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2017-01-05', 3.5);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-05', 'paris', 3.5);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2017-01-05', -1.5);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-05', 'berlin', -1.5);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2017-01-06', 3.6);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-06', 'paris', 3.6);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2017-01-06', 1.6);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-06', 'londres', 1.6);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2017-01-06', -1.6);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-06', 'berlin', -1.6);

INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('paris', '2017-01-07', 3.7);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-07', 'paris', 3.7);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('londres', '2017-01-07', 1.7);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-07', 'londres', 1.7);
INSERT INTO temperature_by_city (city, probe_date, temperature) VALUES ('berlin', '2017-01-07', -1.7);
INSERT INTO temperature_by_probe_date (probe_date, city, temperature) VALUES ('2017-01-07', 'berlin', -1.7);



