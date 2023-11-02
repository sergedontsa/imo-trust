CREATE TABLE IF NOT EXISTS owner
(
    id                VARCHAR(20) PRIMARY KEY NOT NULL,
    first_name        VARCHAR(20)             NOT NULL,
    middle_name       VARCHAR(20),
    last_name         VARCHAR(20)             NOT NULL,
    gender            VARCHAR(10)             NOT NULL,
    registration_date timestamp               NOT NULL,
    last_updated      timestamp               NOT NULL
   );

CREATE OR REPLACE FUNCTION update_last_updated_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.last_updated = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_owner_last_updated
    BEFORE UPDATE
    ON owner
    FOR EACH ROW
EXECUTE PROCEDURE
    update_last_updated_column();
