package com.oocl.training.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Company {
    int id;
    String name;
    List<Employee> employees;
}
