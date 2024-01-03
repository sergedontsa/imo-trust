CREATE TABLE IF NOT EXISTS employee
(
    id                SERIAL PRIMARY KEY,
    employee_id       VARCHAR(50) NOT NULL UNIQUE,
    registration_date timestamp   NOT NULL,
    last_updated      timestamp   NOT NULL
)
