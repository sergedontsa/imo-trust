CREATE TABLE action(
    id SERIAL PRIMARY KEY ,
    title VARCHAR(50) NOT NULL,
    parties VARCHAR(50) NOT NULL,
    parties_id VARCHAR(20) NOT NULL ,
    description VARCHAR(250) NOT NULL,
    registration_date TIMESTAMP   NOT NULL,
    last_updated      TIMESTAMP   NOT NULL

)
