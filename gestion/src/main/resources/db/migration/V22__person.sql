CREATE TABLE IF NOT EXISTS person(
    id VARCHAR(20) NOT NULL PRIMARY KEY,
    first_name        VARCHAR(20)             NOT NULL,
    middle_name       VARCHAR(20),
    last_name         VARCHAR(20)             NOT NULL,
    gender            VARCHAR(10)             NOT NULL,
    registration_date TIMESTAMP               NOT NULL,
    last_updated      TIMESTAMP               NOT NULL
)
