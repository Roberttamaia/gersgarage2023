package com.gers.gers.controllers;

import com.gers.gers.models.Employee;
import com.gers.gers.models.Spares;
import com.gers.gers.service.employee.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class EmployeeController {
    @Autowired
   private employeeService employeeService;
    @GetMapping("/new_employee")
    public String newEmployee(Model model)
    {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "Admin/employees/newEmployee";
    }

    @GetMapping("/all_employees")
    public String storedEmployees(Model model){
        List<Employee> employee = employeeService.showAllEmployee();
        // System.out.println(spares.toString());
        model.addAttribute("employee", employee);
        return "Admin/employees/allEmployee";
    }

    @GetMapping("/get_employee/{id}")
    public String getEmployee(@PathVariable("id") Long id, Model model){
        Employee employee  =employeeService.getEmployee(id);
        model.addAttribute("employee",employee);
        return "Admin/employees/editEmployee";
    }


    @PostMapping("/createEmployee")
    public String StoreSpare(@ModelAttribute("employee") Employee employee, BindingResult result, RedirectAttributes redirAttrs){
        employeeService.createEmployee(employee);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return  "redirect:/new_employee";
        }
        redirAttrs.addFlashAttribute("message", "New Employee Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/new_employee";
    }

    @PostMapping("/update_employee/{id}")
    public String updateSpare(@PathVariable Long id,@ModelAttribute("employee") Employee employee,Model model,BindingResult result,
                              RedirectAttributes redirAttrs){
        //get data

        Employee existingEmployee = employeeService.getEmployee(id);
        existingEmployee.setEmpFirstName(employee.getEmpFirstName());
        existingEmployee.setEmpLastName(employee.getEmpLastName());
        existingEmployee.setEmpGender(employee.getEmpGender());
        existingEmployee.setEmpEmail(employee.getEmpEmail());
        //update data
        employeeService.updateEmployee(employee);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_employees";
        }
        redirAttrs.addFlashAttribute("message", " Employee Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_employees";

    }

    @GetMapping("delete_employee/{id}")
    public String deleteSpare(@PathVariable Long id,BindingResult result,RedirectAttributes redirAttrs){
        employeeService.deleteEmployee(id);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_employees";
        }
        redirAttrs.addFlashAttribute("message", " Employee Deleted");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_employees";
    }


}
