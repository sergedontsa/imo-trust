CREATE TABLE building
(
    id                VARCHAR(50)  NOT NULL PRIMARY KEY,
    designation       VARCHAR(250),
    description       VARCHAR(250),
    category          VARCHAR(250) NOT NULL,
    construction_year INTEGER      NOT NULL,
    number_of_floors  INTEGER      NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL
);
CREATE TABLE apartments
(
    apartment_id        INT PRIMARY KEY,
    apartment_number    VARCHAR(20),
    building_id         VARCHAR(50) NOT NULL,
    num_bedrooms        INT,
    square_footage      DECIMAL(10, 2),
    rent_amount         DECIMAL(10, 2),
    description         VARCHAR(250),
    availability_status VARCHAR(20),
    FOREIGN KEY (building_id) REFERENCES building (id)
);
