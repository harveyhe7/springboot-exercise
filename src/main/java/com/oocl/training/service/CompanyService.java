package com.oocl.training.service;

import com.oocl.training.dao.CompanyDbDao;
import com.oocl.training.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDbDao companyDbDao;

    public void deleteCompanyById(int id) {
        companyDbDao.deleteCompanyById(id);
    }

    public Company updateCompanyName(Company company,Integer id) {
        return companyDbDao.updateCompanyName(company,id);
    }

    public Company createCompany(Company company) {
        return companyDbDao.createCompany(company);
    }

//    public List<Employee> getCompanyAllEmployeesById(int id) {
//        return companyDao.getCompanyAllEmployeesById(id);
//    }

    public Company getCompanyById(int id) {
        return companyDbDao.getCompanyById(id);
    }

    public List<Company> getAllCompanies(Integer page, Integer size) {
        List<Company> companies = companyDbDao.getAllCompanies();
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
