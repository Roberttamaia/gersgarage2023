package com.gers.gers.service.employee;

import com.gers.gers.models.Employee;
import com.gers.gers.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class employeeServImpl implements employeeService{

    @Autowired
    private EmployeeRepo employeeRepo;
    /**
     * @param employee
     * @return
     */
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    /**
     * @return
     */
    @Override
    public List<Employee> showAllEmployee() {
        return employeeRepo.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Employee getEmployee(Long id) {
        return employeeRepo.findById(id).get();
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    /**
     * @param id
     */
    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public long allEmployees() {
        return employeeRepo.allEmployee();
    }
}
