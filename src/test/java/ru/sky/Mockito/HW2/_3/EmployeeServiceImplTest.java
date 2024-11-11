package ru.sky.Mockito.HW2._3;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sky.Mockito.HW2._3.exception.EmployeeAlreadyAddedException;
import ru.sky.Mockito.HW2._3.exception.EmployeeNotFoundException;
import ru.sky.Mockito.HW2._3.service.EmployeeServiceImpl;

import java.util.Collection;

public class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void addEmployee_shouldAddEmployee() {
        Employee employee = new Employee("Пётр", "Сергеев", 3, 15000);
        Employee result = employeeService.addEmployee("Пётр", "Сергеев", 3, 15000);

        assertEquals(employee, result);
        assertTrue(employeeService.getAllEmployees().contains(employee));
    }

    @Test
    void addEmployee_shouldThrowExceptionWhenEmployeeAlreadyExists() {
        employeeService.addEmployee("Пётр", "Сергеев", 3, 15000);

        Exception exception = assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.addEmployee("Пётр", "Сергеев", 3, 15000);
        });

        assertEquals("Такой сотрудник уже существует!", exception.getMessage());
    }

    @Test
    void findEmployee_shouldReturnEmployee() {
        employeeService.addEmployee("Пётр", "Сергеев", 3, 15000);

        Employee result = employeeService.findEmployee("Пётр", "Сергеев", 3, 15000);
        assertNotNull(result);
        assertEquals("Пётр", result.getFirstName());
        assertEquals("Сергеев", result.getLastName());
    }

    @Test
    void findEmployee_shouldThrowExceptionWhenEmployeeNotFound() {
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findEmployee("Пётр", "Сергеев", 3, 15000);
        });

        assertEquals("Такой сотрудник не найден", exception.getMessage());
    }

    @Test
    void removeEmployee_shouldRemoveEmployee() {
        employeeService.addEmployee("Пётр", "Сергеев", 3, 15000);

        Employee result = employeeService.removeEmployee("Пётр", "Сергеев", 3, 15000);
        assertNotNull(result);
        assertEquals("Пётр", result.getFirstName());
        assertEquals("Сергеев", result.getLastName());
        assertFalse(employeeService.getAllEmployees().contains(result));
    }

    @Test
    void removeEmployee_shouldThrowExceptionWhenEmployeeNotFound() {
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.removeEmployee("Пётр", "Сергеев", 3, 15000);
        });

        assertEquals("Такой сотрудник не найден", exception.getMessage());
    }

    @Test
    void getAllEmployees_shouldReturnAllEmployees() {
        Employee employee1 = new Employee("Пётр", "Сергеев", 3, 15000);
        Employee employee2 = new Employee("Пётр", "Сергеев", 3, 15000);

        employeeService.addEmployee("Пётр", "Сергеев", 3, 15000);
        employeeService.addEmployee("Пётр", "Сергеев", 3, 15000);

        Collection<Employee> employees = employeeService.getAllEmployees();
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }
}