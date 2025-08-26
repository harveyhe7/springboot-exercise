package com.oocl.training.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/employees")
public class EmployeeController {
    private ArrayList<Employee> employees = new ArrayList<>(
            List.of(new Employee(1, "Bob", 30, "Male", 8000),
                    new Employee(2, "Charlie", 35, "Male", 9000),
                    new Employee(3, "Alice", 31, "Female", 8000)));

    @GetMapping("")
    public ArrayList<Employee> getEmployees(@RequestParam(required = false) String gender) {
        if (gender == null || gender.isEmpty()) {
            return employees; // 返回所有员工
        }

        ArrayList<Employee> filteredList = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getGender().equalsIgnoreCase(gender)) {
                filteredList.add(emp);
            }
        }
        return filteredList;
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

    @PostMapping("")
    public Employee addEmployee(@RequestBody Employee employee) {
        int newId = employees.size() + 1;
        employee.setId(newId);
        employees.add(employee);
        return employee;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
//        删除指定id的员工
        employees = employees.stream()
                .filter(employee -> employee.getId() != id)
                .collect(Collectors.toCollection(ArrayList::new));

    }

}
