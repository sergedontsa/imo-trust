package com.trust.gestion.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EmployeeDepartmentId implements Serializable {
    private static final long serialVersionUID = 7214648159310853074L;
    @Size(max = 50)
    @NotNull
    @Column(name = "employee_id", nullable = false, length = 50)
    private String employeeId;

    @NotNull
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeDepartmentId entity = (EmployeeDepartmentId) o;
        return Objects.equals(this.departmentId, entity.departmentId) &&
                Objects.equals(this.employeeId, entity.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, employeeId);
    }

}
