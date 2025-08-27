package com.oocl.training.model;

import com.oocl.training.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Company {
    int id;
    String name;
    List<Employee> employees;

    public Company(Integer id, String global_innovators) {
    }
}
