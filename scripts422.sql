CREATE TABLE cars
(
    id    INTEGER PRIMARY KEY,
    brand TEXT,
    model TEXT,
    cost  INTEGER
);
CREATE TABLE person
(
    id             INTEGER PRIMARY KEY,
    name           TEXT,
    age            INTEGER,
    driver_license BOOLEAN,
    car_id         INTEGER REFERENCES cars (id)
)
