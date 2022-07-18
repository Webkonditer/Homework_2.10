package com.example.homework210.controller;

import com.example.homework210.model.Employee;
import com.example.homework210.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")

public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.maxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.minSalary(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> departmentEmployees(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.departmentEmployees(departmentId);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> departmentEmployees() {
            return departmentService.allEmployees();
    }

}
