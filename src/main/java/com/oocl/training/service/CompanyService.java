package com.oocl.training.service;

import com.oocl.training.dao.CompanyDao;
import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;

    public void deleteCompanyById(int id) {
        companyDao.deleteCompanyById(id);
    }

    public Company updateCompanyName(int id) {
        return companyDao.updateCompanyName(id);
    }

    public Company createCompany(Company company) {
        return companyDao.createCompany(company);
    }

    public List<Employee> getCompanyAllEmployeesById(int id) {
        return companyDao.getCompanyAllEmployeesById(id);
    }

    public Company getCompanyById(int id) {
        return companyDao.getCompanyById(id);
    }

    public List<Company> getAllCompanies(Integer page, Integer size) {
        List<Company> companies = companyDao.getAllCompanies();
        if (page != null && size != null) {
            int fromIndex = (page - 1) * size;
            int toIndex = Math.min(fromIndex + size, companies.size());
            if (fromIndex >= companies.size()) {
                return List.of(); // Return empty list if page is out of bounds
            }
            return companies.subList(fromIndex, toIndex);
        }
        return companies;
    }
}
