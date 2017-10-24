CREATE TABLE IF NOT EXISTS my_keyspace.temperature_by_date (
    date date,
    city text,
    temperature float,
    PRIMARY KEY (date, city)
);
