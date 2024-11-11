package ru.sky.Mockito.HW2._3.service;

import org.springframework.stereotype.Service;
import ru.sky.Mockito.HW2._3.Employee;
import ru.sky.Mockito.HW2._3.exception.EmployeeAlreadyAddedException;
import ru.sky.Mockito.HW2._3.exception.EmployeeNotFoundException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final int maxListSize = 10;

    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Пётр", "Петров", 2, 15000),
            new Employee("Иван", "Иванов", 3, 21000),
            new Employee("Семён", "Семёнов", 2, 24000),
            new Employee("Сергей", "Сидоров", 1, 38000),
            new Employee("Тимофей", "Тимофеев", 3, 31000),
            new Employee("Пётр", "Петров", 1, 26000),
            new Employee("Фёдор", "Фёдоров", 1, 27000),
            new Employee("Роман", "Романов", 3, 40000),
            new Employee("Евгений", "Крюков", 2, 25000),
            new Employee("Василий", "Васильев", 3, 38000)
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует!");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees);
    }
}