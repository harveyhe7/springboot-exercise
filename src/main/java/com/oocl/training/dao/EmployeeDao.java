package com.oocl.training.dao;

import com.oocl.training.exception.ResourceNotFoundException;
import com.oocl.training.model.Employee;

import java.util.ArrayList;

public interface EmployeeDao {
    public Employee save(Employee employee) ;

    public ArrayList<Employee> getAllEmployees();

    public ArrayList<Employee> getEmployeesByGender(String gender) throws ResourceNotFoundException ;

    public void remove(int id);

    public Employee updateEmployee(int id, int updatedSalary, int updatedAge) ;

    public Employee getEmployeeById(int id) throws ResourceNotFoundException ;
}
