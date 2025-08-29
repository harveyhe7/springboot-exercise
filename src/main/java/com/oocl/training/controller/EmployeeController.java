package com.oocl.training.controller;

import com.oocl.training.controller.dto.EmployeeRequest;
import com.oocl.training.controller.dto.EmployeeResponse;
import com.oocl.training.controller.mapper.EmployeeMapper;
import com.oocl.training.model.Employee;
import com.oocl.training.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("")
    public List<EmployeeResponse> getEmployees(@RequestParam(required = false) String gender) {
        List<Employee> employees = employeeService.getAllEmployees(gender);
        List<EmployeeResponse> employeeResponses = employeeMapper.toResponse(employees);
        return  employeeResponses;
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        Employee getEmployee = employeeService.getEmployeeById(id);
        EmployeeResponse employeeResponse = employeeMapper.toResponse(getEmployee);
        return employeeResponse;
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable int id,@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEntity(employeeRequest);
        Employee getEmployee = employeeService.updateEmployee(employee, id);
        EmployeeResponse employeeResponse = employeeMapper.toResponse(getEmployee);
        return employeeResponse;
    }

    @PostMapping("")
    public EmployeeResponse addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEntity(employeeRequest);
        Employee newEmployee = employeeService.createEmployee(employee);
        EmployeeResponse employeeResponse = employeeMapper.toResponse(newEmployee);
        return employeeResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.removeEmployee(id);
    }

}
