CREATE TABLE users
(
    email                   VARCHAR(500)        NOT NULL PRIMARY KEY UNIQUE,
    username                VARCHAR(255) UNIQUE NOT NULL,
    password                VARCHAR(255)        NOT NULL,
    account_non_expired     BOOLEAN             NOT NULL DEFAULT TRUE,
    account_non_locked      BOOLEAN             NOT NULL DEFAULT TRUE,
    credentials_non_expired BOOLEAN             NOT NULL DEFAULT TRUE,
    enabled                 BOOLEAN             NOT NULL DEFAULT TRUE,
    role                    VARCHAR(255)        NOT NULL DEFAULT 'ROLE_USER',
    registration_date       TIMESTAMP           NOT NULL,
    last_updated            TIMESTAMP           NOT NULL
);
