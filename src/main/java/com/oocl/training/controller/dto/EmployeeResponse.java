package com.oocl.training.controller.dto;

import com.oocl.training.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Company company;
}
