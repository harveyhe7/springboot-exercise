package com.oocl.training.dao;

import com.oocl.training.exception.ResourceNotFoundException;
import com.oocl.training.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class EmployeeDbDao implements EmployeeDao {

    JpaEmployeeDao jpaEmployeeDao;
    public EmployeeDbDao(JpaEmployeeDao jpaEmployeeDao){
        this.jpaEmployeeDao = jpaEmployeeDao;
    }

    @Override
    public Employee save(Employee employee) {
        return jpaEmployeeDao.save(employee);
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(jpaEmployeeDao.findAll());
    }

    @Override
    public ArrayList<Employee> getEmployeesByGender(String gender) throws ResourceNotFoundException {
        return new ArrayList<>(jpaEmployeeDao.getEmployeeByGender(gender));
    }

    @Override
    public void remove(int id) {
        Employee employee = jpaEmployeeDao.findById(id).orElse(null);
        if (employee != null) {
            employee.setActive(false);
            jpaEmployeeDao.save(employee);
        }
    }

    @Override
    public Employee updateEmployee(int id, int updatedSalary, int updatedAge) {
        Employee employee = jpaEmployeeDao.findById(id).orElse(null);
        if (employee != null) {
            employee.setSalary(updatedSalary);
            employee.setAge(updatedAge);
            jpaEmployeeDao.save(employee);
        }
        return employee;
    }

    @Override
    public Employee getEmployeeById(int id) throws ResourceNotFoundException {

        return jpaEmployeeDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id + "") );
    }
}
