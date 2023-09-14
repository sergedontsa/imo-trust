CREATE TABLE IF NOT EXISTS building_owner
(
    id                serial      NOT NULL,
    building_id       VARCHAR(50) NOT NULL,
    owner_id          VARCHAR(50) NOT NULL,
    registration_date TIMESTAMP   NOT NULL,
    last_updated      TIMESTAMP   NOT NULL,
    FOREIGN KEY (building_id) REFERENCES building (id),
    FOREIGN KEY (owner_id) REFERENCES owner (id),
    PRIMARY KEY (id)
);
