package com.oocl.training.dao;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyMemoryDao implements CompanyDao{
    private final HashMap<Integer, Company> companies = new HashMap<>(Map.of(
//            1, new Company(1, "Acme Corporation", List.of(
//                    new Employee(1, "John Smith", 32, "Male", 5000.0, true,1),
//                    new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true,2)
//            )),
//            2, new Company(2, "TechCom Solutions", List.of(
//                    new Employee(3, "David Williams", 35, "Male", 5500.0, true,1),
//                    new Employee(4, "Emily Brown", 23, "Female", 4500.0, true,1),
//                    new Employee(5, "Michael Jones", 40, "Male", 7000.0, true,2)
//            )),
            1, new Company(1, "Global Innovators"),
            2, new Company(2, "Stellar Enterprises"),
            3, new Company(3, "Nexus Industries")
    ));

    public void deleteCompanyById(int id) {
        companies.remove(id);
    }

    public Company updateCompanyName(Company company, Integer id) {
        Company companyGet = companies.get(id);
        if (companyGet != null) {
            companyGet.setName(company.getName());
        }
        return companyGet;
    }

    public Company createCompany(Company company) {
        company.setId(companies.size() + 1);
        companies.put(company.getId(), company);
        return company;
    }

//    public List<Employee> getCompanyAllEmployeesById(int id) {
//        Company company = companies.get(id);
//        if (company != null) {
//            return company.getEmployees();
//        }
//        return null;
//    }

    public Company getCompanyById(int id) {
        return companies.get(id);
    }

    public List<Company> getAllCompanies() {
        return companies.values().stream().toList();
    }
}
