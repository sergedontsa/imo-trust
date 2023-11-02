CREATE TABLE tenant_billing
(
    id                VARCHAR(20)    NOT NULL PRIMARY KEY,
    tenant_id         VARCHAR(20)    NOT NULL,
    apartment_id      VARCHAR(20)    NOT NULL,
    billing_type      VARCHAR(20)    NOT NULL,
    billing_amount    DECIMAL(10, 2) NOT NULL,
    bill_from         DATE           NOT NULL,
    bill_to           DATE           NOT NULL,
    bill_due_date     DATE           NOT NULL,
    status            VARCHAR(20)    NOT NULL,
    description       VARCHAR(250)   NOT NULL,
    registration_date TIMESTAMP      NOT NULL,
    last_updated      TIMESTAMP      NOT NULL,
    FOREIGN KEY (tenant_id) REFERENCES tenant (id),
    FOREIGN KEY (apartment_id) REFERENCES apartments(id)
);
