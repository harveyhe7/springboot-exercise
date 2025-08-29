package com.oocl.training.controller.dto;

import com.oocl.training.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String name;
    private Integer age;
    private String gender;
    private Boolean active;
    private Integer companyId;
}
