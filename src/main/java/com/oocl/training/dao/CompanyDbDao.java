package com.oocl.training.dao;

import com.oocl.training.model.Company;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDbDao implements CompanyDao{
    JpaCompanyDao jpaCompanyDao;
    public CompanyDbDao(JpaCompanyDao jpaCompanyDao){
        this.jpaCompanyDao = jpaCompanyDao;
    }

    @Override
    public void deleteCompanyById(int id) {
        jpaCompanyDao.deleteById(id);
    }

    @Override
    public Company updateCompanyName(Company company, Integer id) {
        Company companyGet = jpaCompanyDao.getCompanyById(id);
        if (companyGet != null) {
            companyGet.setName(company.getName());
            return jpaCompanyDao.save(companyGet);
        }
        return null;
    }

    @Override
    public Company createCompany(Company company) {
        return jpaCompanyDao.save(company);
    }

    @Override
    public Company getCompanyById(int id) {
        return jpaCompanyDao.findById(id).orElse(null);
    }

    @Override
    public java.util.List<Company> getAllCompanies() {
        return jpaCompanyDao.findAll();
    }
}
