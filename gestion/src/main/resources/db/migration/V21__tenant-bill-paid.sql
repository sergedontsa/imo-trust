CREATE TABLE IF NOT EXISTS tenant_bill_paid
(
    id                VARCHAR(20)    NOT NULL PRIMARY KEY,
    tenant_id         VARCHAR(20)    NOT NULL,
    bill_id           VARCHAR(20)    NOT NULL,
    amount_paid       DECIMAL(10, 2) NOT NULL,
    payment_date      DATE           NOT NULL,
    payment_method    VARCHAR(20),
    description       VARCHAR(250)   NOT NULL,
    registration_date TIMESTAMP      NOT NULL,
    last_updated      TIMESTAMP      NOT NULL,
    FOREIGN KEY (tenant_id) REFERENCES tenant (id),
    FOREIGN KEY (bill_id) REFERENCES tenant_billing (id)
);
