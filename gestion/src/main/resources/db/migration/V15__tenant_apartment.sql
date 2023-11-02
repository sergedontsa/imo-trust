CREATE TABLE IF NOT EXISTS tenant_apartment
(
    id                SERIAL PRIMARY KEY,
    tenant_id         VARCHAR(20) NOT NULL REFERENCES tenant (id),
    apartment_id      VARCHAR(20) NOT NULL REFERENCES apartments (id),
    description       varchar(255),
    registration_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated      TIMESTAMP   NOT NULL
);
ALTER TABLE tenant_apartment
    OWNER TO imogestion;
