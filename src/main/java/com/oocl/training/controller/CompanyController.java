package com.oocl.training.controller;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import com.oocl.training.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    // 1. 获取所有公司
    @GetMapping("")
    public List<Company> getAllCompanies(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return companyService.getAllCompanies(page, size);
    }


    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }


    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        return companyService.getCompanyAllEmployeesById(id);
    }


    @PostMapping("")
    public Company addCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompanyName(@PathVariable int id) {
        return companyService.updateCompanyName(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompanyById(id);
    }


}
