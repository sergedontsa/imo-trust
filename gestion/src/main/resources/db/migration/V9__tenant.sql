CREATE TABLE tenant
(
    id                VARCHAR(20)  NOT NULL PRIMARY KEY,
    first_name        VARCHAR(20)  NOT NULL,
    middle_name       VARCHAR(20)  NOT NULL,
    last_name         VARCHAR(20)  NOT NULL,
    gender            VARCHAR(20)  NOT NULL,
    status            VARCHAR(20)  NOT NULL,
    date_of_birth     DATE         NOT NULL,
    country_of_origin VARCHAR(20)  NOT NULL,
    city_of_origin    VARCHAR(20)  NOT NULL,
    description       VARCHAR(250) NOT NULL,
    contact_type      VARCHAR(20)  NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL
);
