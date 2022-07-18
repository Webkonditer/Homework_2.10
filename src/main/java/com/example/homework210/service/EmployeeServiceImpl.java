package com.example.homework210.service;

import com.example.homework210.model.Employee;
import com.example.homework210.exception.EmployeeAlreadyAddedException;
import com.example.homework210.exception.EmployeeNotFoundException;
import com.example.homework210.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employees = new HashMap<>(Map.of(
            "Егоров Егор", new Employee("Егоров", "Егор", 40000, 1),
            "Аалександров Александр", new Employee("Аалександров", "Аалександр", 50000, 1),
            "Иванов Иван", new Employee("Иванов", "Иван", 60000, 1),
            "Дмитриев Дмитрий", new Employee("Дмитриев", "Дмитрий", 70000, 2),
            "Сергеев Сергей", new Employee("Сергеев", "Сергей", 80000, 2),
            "Николаев Николай", new Employee("Николаев", "Николай", 90000, 2),
            "Евгеньев Евгений", new Employee("Евгеньев", "Евгений", 100000, 3),
            "Евсеев Евсей", new Employee("Евсеев", "Евсей", 90000, 3),
            "Павлов Павел", new Employee("Павлов", "Павел", 80000, 3)
    ));

    private final int employeeNum = 10;

    @Override
    public Employee addNewEmployee(String lastName, String firstName, Integer salary, Integer department) {

        if (employees.size() >= employeeNum){
            throw new EmployeeStorageIsFullException("Все вакансии заняты");
        }
        if (employees.containsKey(lastName + " " + firstName)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        Employee employee = new Employee(lastName, firstName, salary, department);
        employees.put(lastName + " " + firstName, employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String lastName, String firstName) {

        if (employees.containsKey(lastName + " " + firstName)) {
            return employees.get(lastName + " " + firstName);
        }

        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee removeEmployee(String lastName, String firstName) {

        if (employees.containsKey(lastName + " " + firstName)) {
            Employee employee = employees.get(lastName + " " + firstName);
            employees.remove(lastName + " " + firstName);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Map<String, Employee> list() {
        return new HashMap(employees);
    }
}

