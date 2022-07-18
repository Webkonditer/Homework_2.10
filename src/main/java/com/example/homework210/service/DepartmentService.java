package com.example.homework210.service;

import com.example.homework210.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee maxSalary(int departmentId);

    Employee minSalary(int departmentId);

    List<Employee> departmentEmployees(int departmentId);

    Map<Integer, List<Employee>> allEmployees();
}
