CREATE TABLE tenant
(
    id                VARCHAR(20)  NOT NULL PRIMARY KEY,
    first_name        VARCHAR(20)  NOT NULL,
    middle_name       VARCHAR(20)  NOT NULL,
    last_name         VARCHAR(20)  NOT NULL,
    gender            VARCHAR(20)  NOT NULL,
    status            VARCHAR(20)  NOT NULL,
    date_of_birth     DATE         NOT NULL,
    country_of_origin VARCHAR(20)  NOT NULL,
    city_of_origin    VARCHAR(20)  NOT NULL,
    description       VARCHAR(250) NOT NULL,
    contact_type      VARCHAR(20)  NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL
);
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
CREATE TABLE tenant_occupation
(
    id                VARCHAR(20)  NOT NULL PRIMARY KEY,
    tenant_id         VARCHAR(20)  NOT NULL,
    occupation        VARCHAR(20)  NOT NULL,
    description       VARCHAR(250) NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL,
    foreign key (tenant_id) references tenant (id)
);

CREATE TABLE tenant_billing
(
    id                VARCHAR(20)  NOT NULL PRIMARY KEY,
    tenant_id         VARCHAR(20)  NOT NULL,
    billing_type      VARCHAR(20)  NOT NULL,
    billing_amount    VARCHAR(20)  NOT NULL,
    bill_from         DATE         NOT NULL,
    bill_to           DATE         NOT NULL,
    bill_due_date     DATE         NOT NULL,
    description       VARCHAR(250) NOT NULL,
    registration_date TIMESTAMP    NOT NULL,
    last_updated      TIMESTAMP    NOT NULL,
    foreign key (tenant_id) references tenant (id)
)
