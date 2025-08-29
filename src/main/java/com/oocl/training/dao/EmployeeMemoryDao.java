package com.oocl.training.dao;

import com.oocl.training.exception.ResourceNotFoundException;
import com.oocl.training.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Repository
public class EmployeeMemoryDao implements EmployeeDao{
//    private final Map<Integer, Employee> employees = new HashMap<>(Map.of(
//            1, new Employee(1, "John Smith", 32, "Male", 5000.0, true,1),
//            2, new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true,1),
//            3, new Employee(3, "David Williams", 35, "Male", 5500.0, true,1),
//            4, new Employee(4, "Emily Brown", 23, "Female", 4500.0, true,1),
//            5, new Employee(5, "Michael Jones", 40, "Male", 7000.0, true,1)));
private final Map<Integer, Employee> employees = new HashMap<>(Map.of(
        1, new Employee(1, "John Smith", 32, "Male", 5000.0, true),
        2, new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true),
        3, new Employee(3, "David Williams", 35, "Male", 5500.0, true),
        4, new Employee(4, "Emily Brown", 23, "Female", 4500.0, true),
        5, new Employee(5, "Michael Jones", 40, "Male", 7000.0, true)));
    @Override
    public Employee save(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public ArrayList<Employee> getEmployeesByGender(String gender) throws ResourceNotFoundException {
        ArrayList<Employee> filteredList = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getGender().equalsIgnoreCase(gender)) {
                filteredList.add(employee);
            }
        }
        return filteredList;
    }

    @Override
    public void remove(int id) {
        employees.get(id).setActive(false);
    }

    @Override
    public Employee updateEmployee(Employee employee, Integer id) {
        Employee getEmployee = employees.get(id);
        if (getEmployee != null) {
            getEmployee.setSalary(employee.getSalary());
            getEmployee.setAge(employee.getAge());
        }
        return employee;
    }

    @Override
    public Employee getEmployeeById(int id) throws ResourceNotFoundException {
        if (!employees.containsKey(id))
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        return employees.get(id);
    }
}
