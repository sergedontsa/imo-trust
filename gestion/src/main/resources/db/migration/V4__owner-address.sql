CREATE TABLE IF NOT EXISTS owner_address
(
    id                SERIAL,
    civic_number      VARCHAR(10)                       NOT NULL,
    street_name       VARCHAR(50)                       NOT NULL,
    apt_number        VARCHAR(10),
    city              VARCHAR(50),
    province          VARCHAR(50),
    postal_code       VARCHAR(10),
    country           VARCHAR(50),
    owner_id          VARCHAR(20) REFERENCES owner (id) NOT NULL,
    type              VARCHAR(20)                       NOT NULL,
    registration_date TIMESTAMP                         NOT NULL,
    last_updated      TIMESTAMP                         NOT NULL
);

CREATE OR REPLACE FUNCTION update_last_updated_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.last_updated = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_address_last_updated
    BEFORE UPDATE
    ON owner_address
    FOR EACH ROW
EXECUTE PROCEDURE
    update_last_updated_column();
