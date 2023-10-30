CREATE TABLE IF NOT EXISTS building_owner
(
    id                SERIAL PRIMARY KEY,
    building_id       INT       NOT NULL,
    owner_id          INT       NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    last_updated      TIMESTAMP NOT NULL,
    description       TEXT      NOT NULL,
    FOREIGN KEY (building_id) REFERENCES building (id),
    FOREIGN KEY (owner_id) REFERENCES owner (id)
)
