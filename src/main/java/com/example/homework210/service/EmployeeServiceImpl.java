package com.example.homework210.service;

import com.example.homework210.exception.BadNamesException;
import com.example.homework210.model.Employee;
import com.example.homework210.exception.EmployeeAlreadyAddedException;
import com.example.homework210.exception.EmployeeNotFoundException;
import com.example.homework210.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employees = new HashMap<>();

    public final int employeeNum = 10;

    @Override
    public Employee addNewEmployee(String lastName, String firstName, Integer salary, Integer department) {

        if (employees.size() >= employeeNum){
            throw new EmployeeStorageIsFullException("Все вакансии заняты");
        }
        if (employees.containsKey(lastName + " " + firstName)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        lastName = namesCheck(lastName);
        firstName = namesCheck(firstName);
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

    private String namesCheck(String string){
        if(!StringUtils.isAlpha(string)){
            throw new BadNamesException();
        }
        return StringUtils.capitalize(string.toLowerCase());
    }
}

