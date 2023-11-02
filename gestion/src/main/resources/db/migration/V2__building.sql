CREATE TABLE IF NOT EXISTS building
(
    id                VARCHAR(20)  NOT NULL PRIMARY KEY,
    designation       VARCHAR(250),
    description       VARCHAR(250),
    category          VARCHAR(250) NOT NULL,
    construction_year INTEGER      NOT NULL,
    number_of_floors  INTEGER      NOT NULL,
    number_of_units   INTEGER      NOT NULL,
    assigned          BOOLEAN      DEFAULT FALSE NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL
);
