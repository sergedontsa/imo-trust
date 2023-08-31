CREATE TABLE employee_role
(
    name        VARCHAR(50) NOT NULL UNIQUE PRIMARY KEY,
    description VARCHAR(500) DEFAULT NULL:: CHARACTER VARYING
);
INSERT INTO employee_role (name, description)
VALUES ('ADMIN', 'Admin');
INSERT INTO employee_role (name, description)
VALUES ('MANAGER', 'Manager');
INSERT INTO employee_role (name, description)
VALUES ('EMPLOYEE', 'Employee');
INSERT INTO employee_role (name, description)
VALUES ('SUPERVISOR', 'SUPERVISOR');
INSERT INTO employee_role (name, description)
VALUES ('CFO', 'CFO');
INSERT INTO employee_role (name, description)
VALUES ('CTO', 'CTO');
INSERT INTO employee_role (name, description)
VALUES ('CEO', 'CEO');
