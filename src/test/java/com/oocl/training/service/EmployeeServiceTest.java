package com.oocl.training.service;

import com.oocl.training.dao.EmployeeDao;
import com.oocl.training.exception.InvailEmployeeException;
import com.oocl.training.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {


    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService;
    @Test
    void shuold_create_employee_successfully() {
//        given
        Employee inputEmployee = new Employee("oocl",25,"Male",8000,true);
        Employee dbOutMockEmployee = new Employee(6,inputEmployee.getName(),inputEmployee.getAge(),inputEmployee.getGender(),inputEmployee.getSalary(),true);
        Mockito.when(employeeDao.save(Mockito.any(Employee.class))).thenReturn(dbOutMockEmployee);
//        when
        Employee savedEmployee = employeeService.createEmployee(inputEmployee);
//        then
        assertEquals(inputEmployee.getAge(), savedEmployee.getAge());
        assertEquals(inputEmployee.getName(), savedEmployee.getName());
    }

    @Test
    void create_employee_failed_due_to_age_invalid() {
//        given
        Employee inputEmployee = new Employee("oocl",17,"Male",8000,true);

//        when Then
        InvailEmployeeException invailEmployeeException = assertThrows(InvailEmployeeException.class, () -> {
            employeeService.createEmployee(inputEmployee);
        });

        assertEquals("Age must be between 18 and 65", invailEmployeeException.getMessage());
    }

    @Test
    void create_employee_failed_due_to_salary_and_age_invalid() {
//        given
        Employee inputEmployee = new Employee("oocl",30,"Male",8000,true);

//        when Then
        InvailEmployeeException invailEmployeeException = assertThrows(InvailEmployeeException.class, () -> {
            employeeService.createEmployee(inputEmployee);
        });

        assertEquals("Salary must be at least 20000 for employees aged 30 or older", invailEmployeeException.getMessage());
    }

    @Test
    void shuold_update_employee_successfully() {
//        given
        Integer id = 1;
        Employee inputEmployee = new Employee("oocl",20,"Male",8000,true);
        Employee dbOutMockEmployee = new Employee(id,inputEmployee.getName(),inputEmployee.getAge()+1,inputEmployee.getGender(),inputEmployee.getSalary()+1000,true);
        Mockito.when(employeeDao.getEmployeeById(Mockito.any(Integer.class))).thenReturn(inputEmployee);
        Mockito.when(employeeDao.updateEmployee(Mockito.any(Integer.class),Mockito.any(Integer.class),Mockito.any(Integer.class))).thenReturn(dbOutMockEmployee);
//        when
        Employee updatedEmployee = employeeService.updateEmployee(id);
//        then
        assertEquals(inputEmployee.getAge()+1, updatedEmployee.getAge());
        assertEquals(inputEmployee.getSalary()+1000, updatedEmployee.getSalary());
    }

}