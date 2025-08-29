package com.oocl.training.controller.dto;

import com.oocl.training.model.Company;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 65, message = "Age must be at most 65")
    private Integer age;
    private String gender;
    private Boolean active;
    private Integer companyId;
}
