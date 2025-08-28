package com.oocl.training.dao;

import com.oocl.training.model.Company;

import java.util.List;

public interface CompanyDao {
    public void deleteCompanyById(int id) ;

    public Company updateCompanyName(Company company,Integer id) ;

    public Company createCompany(Company company) ;

//    public List<Employee> getCompanyAllEmployeesById(int id) {
//        Company company = companies.get(id);
//        if (company != null) {
//            return company.getEmployees();
//        }
//        return null;
//    }

    public Company getCompanyById(int id) ;

    public List<Company> getAllCompanies();
}
