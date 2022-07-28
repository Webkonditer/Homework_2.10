package com.example.homework210.service;

import com.example.homework210.exception.EmployeeAlreadyAddedException;
import com.example.homework210.exception.EmployeeNotFoundException;
import com.example.homework210.exception.EmployeeStorageIsFullException;
import org.junit.jupiter.api.Test;
import com.example.homework210.model.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    @Test
    void addNewEmployeeTest() {
        Employee result = employeeServiceImpl.addNewEmployee("Сидоров", "Сидор", 60000, 3);
        Employee actual = new Employee("Сидоров", "Сидор", 60000, 3);
        assertEquals(result, actual);
    }

    @Test
    void addExistingEmployee() {
        Employee result = employeeServiceImpl.addNewEmployee("Сидоров", "Сидор", 60000, 3);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeServiceImpl.addNewEmployee("Сидоров", "Сидор", 60000, 3));
    }

    @Test //Проверка на переполнение хранилища
    void addFullStorageEmployee() {
        int countFreeOfEmployes = employeeServiceImpl.employeeNum - employeeServiceImpl.list().size();
        String lastname = "Lastname";
        for (int i = 0; i < countFreeOfEmployes; i++) {
            lastname = lastname + "e";
            employeeServiceImpl.addNewEmployee(lastname, "Firstname", 60000, 3);
        }
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeServiceImpl.addNewEmployee("Сидоров", "Сидор", 60000, 3));
    }

    @Test
    void findEmployeeTest() {
        Employee result = employeeServiceImpl.addNewEmployee("Сидоров", "Сидор", 60000, 3);
        Employee actual = employeeServiceImpl.findEmployee("Сидоров", "Сидор");
        assertEquals(result, actual);
    }

    @Test
    void NotFoundEmployeeTest() {
        if(employeeServiceImpl.list().containsKey("Сидоров Сидор")){
            employeeServiceImpl.removeEmployee("Сидоров", "Сидор");
        }
        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.findEmployee("Сидоров", "Сидор"));
    }

    @Test
    void removeEmployeeTest() {
        employeeServiceImpl.addNewEmployee("Сидоров", "Сидор", 60000, 3);
        employeeServiceImpl.removeEmployee("Сидоров", "Сидор");
        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.findEmployee("Сидоров", "Сидор"));
    }

    @Test
    void removeNotExistingEmployee() {
        if(employeeServiceImpl.list().containsKey("Сидоров Сидор")){
            employeeServiceImpl.removeEmployee("Сидоров", "Сидор");
        }
        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.removeEmployee("Сидоров", "Сидор"));
    }

    @Test
    void listTest() {
        assertNotNull(employeeServiceImpl.list());
    }
}