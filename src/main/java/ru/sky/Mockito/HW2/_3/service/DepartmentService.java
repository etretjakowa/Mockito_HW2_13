package ru.sky.Mockito.HW2._3.service;

import ru.sky.Mockito.HW2._3.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMinSalaryOfDepartment(int department);

    Employee getEmployeeWithMaxSalaryOfDepartment(int department);

    double getSumSalaryEmployeesOfDepartment(int department);

    Map<Integer, List<Employee>> getAllEmployees();

    Collection<Employee> getDepartmentEmployees(int department);

    Collection<Employee> findAll();
}