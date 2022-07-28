package com.example.homework210.service;

import com.example.homework210.exception.EmployeeNotFoundException;
import com.example.homework210.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeServiceImpl;

    private DepartmentServiceImpl out;

    private Map<String, Employee> employees = new HashMap<>(Map.of(
            "Егоров Егор", new Employee("Егоров", "Егор", 40000, 1),
            "Александров Александр", new Employee("Александров", "Александр", 50000, 1),
            "Иванов Иван", new Employee("Иванов", "Иван", 60000, 1),
            "Дмитриев Дмитрий", new Employee("Дмитриев", "Дмитрий", 70000, 2),
            "Сергеев Сергей", new Employee("Сергеев", "Сергей", 80000, 2),
            "Николаев Николай", new Employee("Николаев", "Николай", 90000, 2),
            "Евгеньев Евгений", new Employee("Евгеньев", "Евгений", 100000, 3),
            "Евсеев Евсей", new Employee("Евсеев", "Евсей", 90000, 3)
    ));

    private Map<String, Employee> emptyEmployees = new HashMap<>();

    @BeforeEach
    public void initOut(){
        out = new DepartmentServiceImpl(employeeServiceImpl);
    }




    @Test
    void maxSalaryTest() {
        when(employeeServiceImpl.list())
                .thenReturn(employees);
        Employee result = out.maxSalary(3);
        Employee actual = new Employee("Евгеньев", "Евгений", 100000, 3);
        assertEquals(result, actual);
    }

    @Test
    void maxSalaryNotFound() {
        when(employeeServiceImpl.list())
                .thenReturn(emptyEmployees);
        assertThrows(EmployeeNotFoundException.class, () -> out.maxSalary(3));
    }

    @Test
    void minSalaryTest() {
        when(employeeServiceImpl.list())
                .thenReturn(employees);
        Employee result = out.minSalary(3);
        Employee actual = new Employee("Евсеев", "Евсей", 90000, 3);
        assertEquals(result, actual);
    }

    @Test
    void minSalaryNotFound() {
        when(employeeServiceImpl.list())
                .thenReturn(emptyEmployees);
        assertThrows(EmployeeNotFoundException.class, () -> out.minSalary(3));
    }

    @Test
    void departmentEmployees() {
        when(employeeServiceImpl.list())
                .thenReturn(employees);
        List<Employee> result = out.departmentEmployees(3);
        List<Employee> actual = Arrays.asList(
            new Employee("Евсеев", "Евсей", 90000, 3),
            new Employee("Евгеньев", "Евгений", 100000, 3)
        );
        assertEquals(result, actual);
    }

    @Test
    void allEmployeesTest() {
        when(employeeServiceImpl.list())
                .thenReturn(employees);
        int resultInt = out.allEmployees().hashCode();
        int actualInt = -1259322201;
        assertEquals(resultInt, actualInt);
    }

}