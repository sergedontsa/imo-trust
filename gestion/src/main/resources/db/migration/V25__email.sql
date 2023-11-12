CREATE TABLE email
(
    id                SERIAL PRIMARY KEY,
    entity_id         VARCHAR(50) NOT NULL,
    distribution      BOOLEAN     NOT NULL DEFAULT FALSE,
    email             VARCHAR(50) NOT NULL,
    registration_date TIMESTAMP   NOT NULL,
    last_updated      TIMESTAMP   NOT NULL
)
