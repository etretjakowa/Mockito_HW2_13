package ru.sky.Mockito.HW2._3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sky.Mockito.HW2._3.exception.EmployeeDepartmentNotFoundException;
import ru.sky.Mockito.HW2._3.service.DepartmentServiceImpl;
import ru.sky.Mockito.HW2._3.service.EmployeeService;

import java.util.*;

public class DepartmentServiceImplTest {

    private EmployeeService employeeService;
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeService.class);
        departmentService = new DepartmentServiceImpl(employeeService);
    }

    @Test
    void getEmployeeWithMinSalaryOfDepartment_shouldReturnEmployeeWithMinSalary() {
        Employee employee1 = new Employee("Сергей", "Иванов", 3, 38000);
        Employee employee2 = new Employee("Пётр", "Сергеев", 3, 15000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        Employee result = departmentService.getEmployeeWithMinSalaryOfDepartment(3);

        assertEquals(employee1, result);
    }

    @Test
    void getEmployeeWithMinSalaryOfDepartment_shouldThrowExceptionWhenDepartmentNotFound() {
        when(departmentService.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(EmployeeDepartmentNotFoundException.class, () -> {
            departmentService.getEmployeeWithMinSalaryOfDepartment(1);
        });

        assertEquals("Отдел не найден", exception.getMessage());
    }

    @Test
    void getEmployeeWithMaxSalaryOfDepartment_shouldReturnEmployeeWithMaxSalary() {
        Employee employee1 = new Employee("Сергей", "Иванов", 3, 38000);
        Employee employee2 = new Employee("Пётр", "Сергеев", 3, 15000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        Employee result = departmentService.getEmployeeWithMaxSalaryOfDepartment(3);

        assertEquals(employee2, result);
    }

    @Test
    void getEmployeeWithMaxSalaryOfDepartment_shouldThrowExceptionWhenDepartmentNotFound() {
        when(departmentService.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(EmployeeDepartmentNotFoundException.class, () -> {
            departmentService.getEmployeeWithMaxSalaryOfDepartment(1);
        });

        assertEquals("Отдел не найден", exception.getMessage());
    }

    @Test
    void getSumSalaryEmployeesOfDepartment_shouldReturnSumOfSalaries() {
        Employee employee1 = new Employee("Сергей", "Иванов", 3, 38000);
        Employee employee2 = new Employee("Пётр", "Сергеев", 3, 15000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        double sum = departmentService.getSumSalaryEmployeesOfDepartment(3);

        assertEquals(53000, sum);
    }

    @Test
    void getAllEmployees_shouldReturnEmployeesGroupedByDepartment() {
        Employee employee1 = new Employee("Сергей", "Иванов", 3, 38000);
        Employee employee2 = new Employee("Пётр", "Сергеев", 3, 15000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        Map<Integer, List<Employee>> result = departmentService.getAllEmployees();

        assertTrue(result.containsKey(3));
        assertTrue(result.containsKey(3));
    }

    @Test
    void getDepartmentEmployees_shouldReturnEmployeesOfDepartment() {
        Employee employee1 = new Employee("Сергей", "Иванов", 3, 38000);
        Employee employee2 = new Employee("Пётр", "Сергеев", 3, 15000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        Collection<Employee> result = departmentService.getDepartmentEmployees(3);

        assertTrue(result.contains(employee1));
        assertTrue(result.contains(employee2));
    }
}