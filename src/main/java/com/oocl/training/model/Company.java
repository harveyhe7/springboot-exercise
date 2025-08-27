package com.oocl.training.model;

import com.oocl.training.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    int id;
    String name;
    List<Employee> employees;

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
