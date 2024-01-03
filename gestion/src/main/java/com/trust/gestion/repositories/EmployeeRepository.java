package com.trust.gestion.repositories;

import com.trust.gestion.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query("select e from EmployeeEntity e where e.employeeId = :employeeId")
    EmployeeEntity findByEmployeeId(@Param("employeeId") String employeeId);
}
