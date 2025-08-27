package com.oocl.training.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    int id;
    String name;
    int age;
    String gender;
    double salary;
    boolean active;

    public Employee(String name, int age, String gender, double salary, boolean active) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.active = active;
    }
}
