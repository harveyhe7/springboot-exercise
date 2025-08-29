package com.oocl.training.controller.mapper;


import com.oocl.training.controller.dto.EmployeeRequest;
import com.oocl.training.controller.dto.EmployeeResponse;
import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }

    public List<EmployeeResponse> toResponse(List<Employee> employees) {
        return employees.stream()
                .map(this::toResponse)
                .toList();
    }

    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        Company company = new Company(employeeRequest.getCompanyId(), null);
        employee.setCompany(company);
        return employee;
    }
}
