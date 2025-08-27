package com.oocl.training.dao;

import com.oocl.training.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Repository
public class EmployeeDao {
    private final Map<Integer, Employee> employees = new HashMap<>(Map.of(
            1, new Employee(1, "John Smith", 32, "Male", 5000.0, true),
            2, new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true),
            3, new Employee(3, "David Williams", 35, "Male", 5500.0, true),
            4, new Employee(4, "Emily Brown", 23, "Female", 4500.0, true),
            5, new Employee(5, "Michael Jones", 40, "Male", 7000.0, true)));

    public Employee save(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.put(employee.getId(), employee);
        return employee;
    }

    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public ArrayList<Employee> getGenderEmployees(String gender) {
        ArrayList<Employee> filteredList = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getGender().equalsIgnoreCase(gender)) {
                filteredList.add(employee);
            }
        }
        return filteredList;
    }

    public void remove(int id) {
        employees.get(id).setActive(false);
    }

    public Employee updateEmployee(int id, int updatedSalary, int updatedAge) {
        Employee employee = employees.get(id);
        if (employee != null) {
            employee.setSalary(updatedSalary);
            employee.setAge(updatedAge);
        }
        return employee;
    }

    public Employee getEmployeeById(int id) {
        return employees.get(id);
    }
}
