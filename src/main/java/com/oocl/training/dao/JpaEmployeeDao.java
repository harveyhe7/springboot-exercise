package com.oocl.training.dao;

import com.oocl.training.model.Employee;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeDao extends JpaRepository<Employee, Integer> {
    List<Employee> getEmployeeByGender(String gender);
    Employee getEmployeeById(Integer id);
}
