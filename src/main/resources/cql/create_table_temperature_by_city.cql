CREATE TABLE IF NOT EXISTS temperature_by_city (
    city text,
    probe_date date,
    temperature float,
    PRIMARY KEY ((city), probe_date)
)
WITH CLUSTERING ORDER BY (probe_date desc);