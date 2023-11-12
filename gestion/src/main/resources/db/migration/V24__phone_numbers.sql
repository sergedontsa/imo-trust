CREATE TABLE telephones
(
    id                SERIAL PRIMARY KEY,
    entity_id         VARCHAR(255) NOT NULL,
    area_code         VARCHAR(255) NOT NULL,
    number            VARCHAR(255) NOT NULL,
    carrier           VARCHAR(255) NOT NULL,
    country           VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL
)
