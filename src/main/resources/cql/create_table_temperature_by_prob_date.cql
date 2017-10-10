CREATE TABLE IF NOT EXISTS temperature_by_probe_date (
    probe_date date,
    city text,
    temperature float,
    PRIMARY KEY (probe_date, city)
);
