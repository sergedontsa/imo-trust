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
CREATE TABLE IF NOT EXISTS contractor_contact_information
(
    id                           SERIAL PRIMARY KEY,
    contractor_id                VARCHAR(20) REFERENCES contractorEntity (id) NOT NULL,
    email                        VARCHAR(255)                           NOT NULL,
    cell_phone_number            VARCHAR(20)                            NOT NULL,
    cell_phone_number_extension  VARCHAR(10),
    work_phone_number            VARCHAR(20),
    work_phone_number_extension  VARCHAR(10),
    home_phone_number            VARCHAR(20),
    home_phone_number_extension  VARCHAR(10),
    other_phone_number           VARCHAR(20),
    other_phone_number_extension VARCHAR(10),
    registration_date            TIMESTAMP                              NOT NULL,
    last_updated                 TIMESTAMP                              NOT NULL

);

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
