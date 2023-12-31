CREATE TABLE IF NOT EXISTS identification
(
    id                SERIAL PRIMARY KEY,
    entity_id          VARCHAR(20) NOT NULL,
    type_id           VARCHAR(50) NOT NULL,
    issue_country     VARCHAR(50) NOT NULL,
    valid_from        DATE        NOT NULL,
    valid_to          DATE        NOT NULL,
    description       VARCHAR(250),
    registration_date timestamp   NOT NULL,
    last_updated      timestamp   NOT NULL
);

CREATE OR REPLACE FUNCTION update_last_updated_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.last_updated = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_identification_last_updated
    BEFORE UPDATE
    ON owner
    FOR EACH ROW
EXECUTE PROCEDURE
    update_last_updated_column();
