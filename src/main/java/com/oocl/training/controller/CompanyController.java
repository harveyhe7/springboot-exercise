package com.oocl.training.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private List<Company> companyList = new ArrayList<>(Arrays.asList(
            new Company(1, "OOCL", Arrays.asList(
            new Employee(1, "Bob", 30, "Male", 8000),
            new Employee(2, "Alice", 28, "Female", 7500)
            )),
            new Company(2, "COSCO", Arrays.asList(
                    new Employee(3, "Charlie", 35, "Male", 9000)
            )),
            new Company(3, "MAERSK", new ArrayList<>())
    ));


    // 1. 获取所有公司
    @GetMapping("")
    public List<Company> getAllCompanies(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return companyList.stream()
                    .skip((long) (page - 1) * size)
                    .limit(size)
                    .collect(Collectors.toList());
        }
        return companyList;
    }


    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        return companyList.stream()
                .filter(c -> c.getId() == id)
                .map(Company::getEmployees)
                .findFirst()
                .orElse(Collections.emptyList());
    }



}
