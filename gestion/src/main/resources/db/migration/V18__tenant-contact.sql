CREATE TABLE tenant_contact
(
    id                VARCHAR(20)  NOT NULL PRIMARY KEY,
    tenant_id         VARCHAR(20)  NOT NULL,
    phone_number_1    VARCHAR(20)  NOT NULL,
    phone_number_2    VARCHAR(20),
    description       VARCHAR(250) NOT NULL,
    email             VARCHAR(20)  NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL,
    foreign key (tenant_id) references tenant (id)
);
