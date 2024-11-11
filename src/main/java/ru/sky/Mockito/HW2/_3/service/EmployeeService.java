package ru.sky.Mockito.HW2._3.service;

import ru.sky.Mockito.HW2._3.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, int department, int salary);

    Employee findEmployee(String firstName, String lastName, int department, int salary);

    Employee removeEmployee(String firstName, String lastName, int department, int salary);

    Collection<Employee> getAllEmployees();
}
