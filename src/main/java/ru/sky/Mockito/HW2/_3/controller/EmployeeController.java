package ru.sky.Mockito.HW2._3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sky.Mockito.HW2._3.Employee;
import ru.sky.Mockito.HW2._3.service.EmployeeServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/allEmployees")
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}