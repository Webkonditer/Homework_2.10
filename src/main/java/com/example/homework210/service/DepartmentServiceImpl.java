package com.example.homework210.service;

import com.example.homework210.exception.EmployeeNotFoundException;
import com.example.homework210.model.Employee;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalary(int departmentId) {
        return employeeService.list().values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee minSalary(int departmentId) {
        return employeeService.list().values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<Employee> departmentEmployees(int departmentId) {

        return  employeeService.list().values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> allEmployees() {
        return  employeeService.list().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
