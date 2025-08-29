package com.oocl.training.service;

import com.oocl.training.dao.EmployeeDao;
import com.oocl.training.dao.EmployeeDbDao;
import com.oocl.training.exception.InvailEmployeeException;
import com.oocl.training.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class EmployeeService {
//    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDbDao employeeDbDao) {
        this.employeeDao = employeeDbDao;
    }

    public Employee createEmployee(Employee employee) {
//        if(!(employee.getAge() > 18 && employee.getAge() < 65)) {
//            throw new InvailEmployeeException("Age must be between 18 and 65");
//        }

        if(employee.getSalary() < 20000 && employee.getAge() >= 30) {
            throw new InvailEmployeeException("Salary must be at least 20000 for employees aged 30 or older");
        }
        employee.setActive(true);
        return employeeDao.save(employee);
    }

    public ArrayList<Employee> getAllEmployees(String gender) {
        if ((gender == null || gender.isEmpty())) {
            return employeeDao.getAllEmployees();
        }

        return employeeDao.getEmployeesByGender(gender);
    }

    public void removeEmployee(int id) {
        employeeDao.remove(id);
    }

    public Employee updateEmployee(Employee employee,Integer id) {
        Employee getEmployee = employeeDao.getEmployeeById(id);
        if (!employee.isActive()) {
            throw new InvailEmployeeException("Cannot update an inactive employee");
        }
        return employeeDao.updateEmployee(employee,id);
    }

    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }
}
