CREATE TABLE IF NOT EXISTS apartments
(
    id                VARCHAR(20) PRIMARY KEY,
    apartment_number  VARCHAR(20),
    building_id       VARCHAR(50) NOT NULL,
    num_bedrooms      INT,
    square_footage    DECIMAL(10, 2),
    rent_amount       DECIMAL(10, 2),
    description       VARCHAR(250),
    occupant          INT NOT NULL,
    status            VARCHAR(20) NOT NULL,
    registration_date TIMESTAMP   NOT NULL,
    last_updated      TIMESTAMP   NOT NULL,
    FOREIGN KEY (building_id) REFERENCES building (id)
);
