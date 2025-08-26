package com.oocl.training.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/employees")
public class EmployeeController {
    private ArrayList<Employee> employees = new ArrayList<>(
            List.of(new Employee(1, "Bob", 30, "Male", 8000),
                    new Employee(2, "Charlie", 35, "Male", 9000),
                    new Employee(3, "Alice", 31, "Female", 8000)));

    @GetMapping("")
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                Employee employee = employees.get(i);
//                设置年龄和薪水加1，1k
                employee.setAge(employee.getAge() + 1);
                employee.setSalary(employee.getSalary() + 1000);
                return employee;
            }
        }
        return null;
    }
}
