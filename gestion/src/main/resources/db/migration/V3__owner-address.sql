CREATE TABLE IF NOT EXISTS owner_address (
    id serial,
    civic_number varchar(10) NOT NULL,
    street_name varchar(50) NOT NULL,
    apt_number varchar(10),
    city varchar(50) ,
    province varchar(50),
    postal_code varchar(10),
    country varchar(50),
    owner_id varchar(20) references owner(id) NOT NULL,
    type varchar(20) NOT NULL,
    registration_date timestamp NOT NULL ,
    last_updated timestamp NOT NULL
);

CREATE OR REPLACE FUNCTION update_last_updated_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.last_updated = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_address_last_updated BEFORE UPDATE
    ON owner_address FOR EACH ROW EXECUTE PROCEDURE
    update_last_updated_column();
