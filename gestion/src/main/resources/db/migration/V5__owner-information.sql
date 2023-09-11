CREATE TABLE IF NOT EXISTS owner_information
(
    id                SERIAL PRIMARY KEY,
    owner_id          VARCHAR(20) REFERENCES owner (id) NOT NULL,
    date_of_birth     DATE                              NOT NULL,
    nationality       VARCHAR(50)                       NOT NULL,
    country_of_birth  VARCHAR(50)                       NOT NULL,
    registration_date timestamp                         NOT NULL,
    last_updated      timestamp                         NOT NULL

);
CREATE OR REPLACE FUNCTION update_last_updated_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.last_updated = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER owner_information_updated
    BEFORE UPDATE
    ON owner_address
    FOR EACH ROW
EXECUTE PROCEDURE
    update_last_updated_column();
