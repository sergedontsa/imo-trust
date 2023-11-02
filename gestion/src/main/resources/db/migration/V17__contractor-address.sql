CREATE TABLE IF NOT EXISTS contractor_address
(
    id                SERIAL,
    civic_number      VARCHAR(10)                            NOT NULL,
    street_name       VARCHAR(50)                            NOT NULL,
    apt_number        VARCHAR(10),
    city              VARCHAR(50),
    province          VARCHAR(50),
    postal_code       VARCHAR(10),
    country           VARCHAR(50),
    contractor_id     VARCHAR(20) REFERENCES contractorEntity (id) NOT NULL,
    type              VARCHAR(20)                            NOT NULL,
    registration_date TIMESTAMP                              NOT NULL,
    last_updated      TIMESTAMP                              NOT NULL
);
