package ru.sky.Mockito.HW2._3;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.sky.Mockito.HW2._3.exception.EmployeeDepartmentNotFoundException;
import ru.sky.Mockito.HW2._3.service.DepartmentServiceImpl;
import ru.sky.Mockito.HW2._3.service.EmployeeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DepartmentServiceImplMockitoTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employees = new ArrayList<>();
        employees.add(new Employee("Сергей", "Сидоров", 1, 38000));
        employees.add(new Employee("Пётр", "Петров", 2, 15000));
        employees.add(new Employee("Тимофей", "Тимофеев", 3, 31000));
        employees.add(new Employee("Роман", "Романов", 3, 40000));
        employees.add(new Employee("Василий", "Васильев", 3, 38000));

        when(departmentService.findAll()).thenReturn(employees);
    }

    @Test
    void getEmployeeWithMinSalaryOfDepartment_shouldReturnEmployeeWithMinSalary() {
        Employee result = departmentService.getEmployeeWithMinSalaryOfDepartment(1);
        assertEquals("Пётр", result.getFirstName());
        assertEquals(15000, result.getSalary());
    }

    @Test
    void getEmployeeWithMinSalaryOfDepartment_shouldThrowExceptionWhenDepartmentNotFound() {
        Exception exception = assertThrows(EmployeeDepartmentNotFoundException.class, () -> {
            departmentService.getEmployeeWithMinSalaryOfDepartment(3);
        });
        assertEquals("Отдел не найден", exception.getMessage());
    }

    @Test
    void getEmployeeWithMaxSalaryOfDepartment_shouldReturnEmployeeWithMaxSalary() {
        Employee result = departmentService.getEmployeeWithMaxSalaryOfDepartment(2);
        assertEquals("Василий", result.getFirstName());
        assertEquals(38000, result.getSalary());
    }

    @Test
    void getEmployeeWithMaxSalaryOfDepartment_shouldThrowExceptionWhenDepartmentNotFound() {
        Exception exception = assertThrows(EmployeeDepartmentNotFoundException.class, () -> {
            departmentService.getEmployeeWithMaxSalaryOfDepartment(3);
        });
        assertEquals("Отдел не найден", exception.getMessage());
    }

    @Test
    void getSumSalaryEmployeesOfDepartment_shouldReturnSumOfSalaries() {
        double sum = departmentService.getSumSalaryEmployeesOfDepartment(3);
        assertEquals(109000, sum);
    }

    @Test
    void getAllEmployees_shouldReturnMapOfEmployeesByDepartment() {
        Map<Integer, List<Employee>> result = departmentService.getAllEmployees();
        assertEquals(3, result.size());
        assertTrue(result.containsKey(2));
        assertTrue(result.containsKey(1));
        assertEquals(3, result.get(1).size());
        assertEquals(3, result.get(2).size());
    }

    @Test
    void getDepartmentEmployees_shouldReturnEmployeesOfDepartment() {
        Collection<Employee> result = departmentService.getDepartmentEmployees(3);
        assertEquals(3, result.size());
        assertTrue(result.stream().anyMatch(e -> e.getFirstName().equals("Роман")));
        assertTrue(result.stream().anyMatch(e -> e.getFirstName().equals("Василий")));
    }
}