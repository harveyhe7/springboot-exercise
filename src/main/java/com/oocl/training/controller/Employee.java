package com.oocl.training.controller;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    int id;
    String name;
    int age;
    String gender;
    int salary;
}
