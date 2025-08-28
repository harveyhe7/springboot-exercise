package com.oocl.training.dao;

import com.oocl.training.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCompanyDao extends JpaRepository<Company,Integer> {
    Company getCompanyById(Integer id);
}
