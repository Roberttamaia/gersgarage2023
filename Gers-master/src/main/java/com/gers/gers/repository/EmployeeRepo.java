package com.gers.gers.repository;

import com.gers.gers.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT COUNT(*) FROM Employee")
    long allEmployee();
}
