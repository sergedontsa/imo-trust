CREATE TABLE claim
(
    id                VARCHAR(20) PRIMARY KEY,
    tile              VARCHAR(20) NOT NULL,
    date              DATE        NOT NULL,
    subject_type      VARCHAR(20) NOT NULL,
    subject_id        VARCHAR(20) NOT NULL,
    severity          VARCHAR(20) NOT NULL,
    status            VARCHAR(20) NOT NULL,
    amount            DECIMAL(10, 2),
    registration_date TIMESTAMP   NOT NULL,
    last_updated      TIMESTAMP   NOT NULL
)
