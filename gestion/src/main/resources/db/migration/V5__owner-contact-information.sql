CREATE TABLE IF NOT EXISTS owner_contact_information
(
    id                           serial PRIMARY KEY,
    owner_id                     VARCHAR(20) REFERENCES owner (id) NOT NULL,
    email                        VARCHAR(255)                      NOT NULL,
    cell_phone_number            VARCHAR(20)                       NOT NULL,
    cell_phone_number_extension  VARCHAR(10),
    work_phone_number            VARCHAR(20),
    work_phone_number_extension  VARCHAR(10),
    home_phone_number            VARCHAR(20),
    home_phone_number_extension  VARCHAR(10),
    other_phone_number           VARCHAR(20),
    other_phone_number_extension VARCHAR(10),
    registration_date            timestamp                         NOT NULL,
    last_updated                 timestamp                         NOT NULL

);
CREATE OR REPLACE FUNCTION update_last_updated_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.last_updated = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER owner_contact_information_updated
    BEFORE UPDATE
    ON owner_address
    FOR EACH ROW
EXECUTE PROCEDURE
    update_last_updated_column();
