CREATE TABLE IF NOT EXISTS contractorEntity
(
    id                VARCHAR(20) PRIMARY KEY,
    first_name        VARCHAR(20)  NOT NULL,
    middle_name       VARCHAR(20),
    last_name         VARCHAR(20)  NOT NULL,
    specialization    VARCHAR(255) NOT NULL,
    registration_date timestamp    NOT NULL,
    last_updated      timestamp    NOT NULL
);
