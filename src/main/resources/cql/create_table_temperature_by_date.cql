CREATE TABLE IF NOT EXISTS temperature_by_date (
    date date,
    city text,
    temperature float,
    PRIMARY KEY (date, city)
);
