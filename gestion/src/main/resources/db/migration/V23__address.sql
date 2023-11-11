CREATE TABLE IF NOT EXISTS address
(
    id                SERIAL PRIMARY KEY,
    entity_id         VARCHAR(20)                       NOT NULL,
    civic_number      VARCHAR(10)                       NOT NULL,
    street_name       VARCHAR(50)                       NOT NULL,
    apt_number        VARCHAR(10),
    city              VARCHAR(50),
    province          VARCHAR(50),
    postal_code       VARCHAR(10),
    country           VARCHAR(50),
    type              VARCHAR(20)                       NOT NULL,
    registration_date TIMESTAMP                         NOT NULL,
    last_updated      TIMESTAMP                         NOT NULL
)
