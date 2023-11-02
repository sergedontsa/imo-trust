CREATE TABLE tenant_occupation
(
    id                VARCHAR(20)  NOT NULL PRIMARY KEY,
    tenant_id         VARCHAR(20)  NOT NULL,
    occupation        VARCHAR(20)  NOT NULL,
    employer_name     VARCHAR(20)  NOT NULL,
    employer_address  VARCHAR(20)  NOT NULL,
    employer_phone    VARCHAR(20)  NOT NULL,
    description       VARCHAR(250) NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL,
    foreign key (tenant_id) references tenant (id)
);
