package com.gers.gers.service.employee;

import com.gers.gers.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface employeeService {

    Employee createEmployee(Employee employee);

    List<Employee> showAllEmployee();

    Employee getEmployee(Long id);

    Employee updateEmployee(Employee employee);

    void  deleteEmployee(Long id);

    long allEmployees();
}
