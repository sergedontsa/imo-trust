CREATE TABLE IF NOT EXISTS employee_role
(
    name        VARCHAR(50) NOT NULL UNIQUE PRIMARY KEY,
    description VARCHAR(500) DEFAULT NULL:: CHARACTER VARYING
);
INSERT INTO employee_role (name, description) VALUES ('ADMIN', 'Admin');
INSERT INTO employee_role (name, description) VALUES ('MANAGER', 'Manager');
INSERT INTO employee_role (name, description) VALUES ('EMPLOYEE', 'EmployeeEntity');
INSERT INTO employee_role (name, description) VALUES ('SUPERVISOR', 'SUPERVISOR');
INSERT INTO employee_role (name, description) VALUES ('CFO', 'CFO');
INSERT INTO employee_role (name, description) VALUES ('CTO', 'CTO');
INSERT INTO employee_role (name, description) VALUES ('CEO', 'CEO');

CREATE TABLE IF NOT EXISTS department
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(500) DEFAULT NULL:: CHARACTER VARYING
);
INSERT INTO department (name, description) VALUES ('IT', 'IT');
INSERT INTO department (name, description) VALUES ('HR', 'HR');
INSERT INTO department (name, description) VALUES ('FINANCE', 'FINANCE');
INSERT INTO department (name, description) VALUES ('SALES', 'SALES');
INSERT INTO department (name, description) VALUES ('MARKETING', 'MARKETING');
INSERT INTO department (name, description) VALUES ('ADMIN', 'ADMIN');
INSERT INTO department (name, description) VALUES ('OPERATIONS', 'OPERATIONS');
INSERT INTO department (name, description) VALUES ('RESEARCH', 'RESEARCH');
INSERT INTO department (name, description) VALUES ('DEVELOPMENT', 'DEVELOPMENT');
INSERT INTO department (name, description) VALUES ('QUALITY', 'QUALITY');
INSERT INTO department (name, description) VALUES ('MANAGEMENT', 'MANAGEMENT');
INSERT INTO department (name, description) VALUES ('EXECUTIVE', 'EXECUTIVE');
INSERT INTO department (name, description) VALUES ('BOARD', 'BOARD');
INSERT INTO department (name, description) VALUES ('LEGAL', 'LEGAL');
