CREATE TABLE IF NOT EXISTS temperature_by_city (
    city text,
    date date,
    temperature float,
    PRIMARY KEY ((city), date)
)
WITH CLUSTERING ORDER BY (date desc);

CREATE TABLE IF NOT EXISTS temperature_by_date (
    date date,
    city text,
    temperature float,
    PRIMARY KEY (date, city)
);
