CREATE TABLE IF NOT EXISTS employee_department
(
    employee_id   VARCHAR(50) NOT NULL,
    department_id INT NOT NULL,
    PRIMARY KEY (employee_id, department_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
    FOREIGN KEY (department_id) REFERENCES department (id)
)
