CREATE TABLE IF NOT EXISTS temperature_by_city (
    city text,
    probe_date date,
    temperature float,
    PRIMARY KEY ((city), probe_date)
)
WITH CLUSTERING ORDER BY (probe_date desc);

CREATE TABLE IF NOT EXISTS temperature_by_probe_date (
    probe_date date,
    city text,
    temperature float,
    PRIMARY KEY (probe_date, city)
);
