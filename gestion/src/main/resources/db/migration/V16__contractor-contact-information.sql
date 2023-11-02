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
