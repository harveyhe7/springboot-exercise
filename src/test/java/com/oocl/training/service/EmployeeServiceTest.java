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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {


    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shuold_create_employee_successfully() {
//        given
        Employee inputEmployee = new Employee("oocl", 25, "Male", 8000, true);
        Employee dbOutMockEmployee = new Employee(6, inputEmployee.getName(), inputEmployee.getAge(), inputEmployee.getGender(), inputEmployee.getSalary(), true);
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
        Employee inputEmployee = new Employee("oocl", 17, "Male", 8000, true);

//        when Then
        InvailEmployeeException invailEmployeeException = assertThrows(InvailEmployeeException.class, () -> {
            employeeService.createEmployee(inputEmployee);
        });

        assertEquals("Age must be between 18 and 65", invailEmployeeException.getMessage());
    }

    @Test
    void create_employee_failed_due_to_salary_and_age_invalid() {
//        given
        Employee inputEmployee = new Employee("oocl", 30, "Male", 8000, true);

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
        Employee inputEmployee = new Employee("oocl", 20, "Male", 8000, true);
        Employee dbOutMockEmployee = new Employee(id, inputEmployee.getName(), inputEmployee.getAge() + 1, inputEmployee.getGender(), inputEmployee.getSalary() + 1000, true);
        Mockito.when(employeeDao.getEmployeeById(Mockito.any(Integer.class))).thenReturn(inputEmployee);
        Mockito.when(employeeDao.updateEmployee(Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(dbOutMockEmployee);
//        when
        Employee updatedEmployee = employeeService.updateEmployee(id);
//        then
        assertEquals(inputEmployee.getAge() + 1, updatedEmployee.getAge());
        assertEquals(inputEmployee.getSalary() + 1000, updatedEmployee.getSalary());
        verify(employeeDao, times(1)).updateEmployee(id, 1000, 1);
        verify(employeeDao, times(1)).getEmployeeById(id);
    }

    @Test
    void get_employees_by_id_successfully() {
//        given
        int id = 1;
        Employee dbOutMockEmployee = new Employee(1, "oocl", 20, "Male", 8000, true);
        Mockito.when(employeeDao.getEmployeeById(Mockito.any(Integer.class))).thenReturn(dbOutMockEmployee);
//        when
        Employee employee = employeeService.getEmployeeById(id);
//        then
        assertEquals(dbOutMockEmployee.getName(), employee.getName());
        assertEquals(dbOutMockEmployee.getAge(), employee.getAge());
        verify(employeeDao, times(1)).getEmployeeById(id);
    }

    @Test
    void delete_employee_by_id_successfully() {
//        given
        int id = 1;
//        when
        employeeService.removeEmployee(id);
//        then
        verify(employeeDao, times(1)).remove(id);
    }

    @Test
    void get_all_employees_successfully() {
//        given
        Map<Integer, Employee> employees = new HashMap<>(Map.of(
                1, new Employee(1, "John Smith", 32, "Male", 5000.0, true),
                2, new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true),
                3, new Employee(3, "David Williams", 35, "Male", 5500.0, true),
                4, new Employee(4, "Emily Brown", 23, "Female", 4500.0, true),
                5, new Employee(5, "Michael Jones", 40, "Male", 7000.0, true)));

        ArrayList<Employee> dbOutMockEmployees = new ArrayList<>(employees.values());

        Mockito.when(employeeDao.getAllEmployees()).thenReturn(dbOutMockEmployees);
//        when
        ArrayList<Employee> resultEmployee =  employeeService.getAllEmployees(null);
//        then
        verify(employeeDao, times(1)).getAllEmployees();
        verify(employeeDao, times(0)).getGenderEmployees(Mockito.any(String.class));
        assertEquals(5, resultEmployee.size());
    }

    @Test
    void get_employees_by_gender() {
//        given
        String gender = "Male";
        Map<Integer, Employee> employees = new HashMap<>(Map.of(
                1, new Employee(1, "John Smith", 32, "Male", 5000.0, true),
                2, new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true),
                3, new Employee(3, "David Williams", 35, "Male", 5500.0, true),
                4, new Employee(4, "Emily Brown", 23, "Female", 4500.0, true),
                5, new Employee(5, "Michael Jones", 40, "Male", 7000.0, true)));
        employees.remove(2);
        employees.remove(4);
        ArrayList<Employee> dbOutMockEmployees = new ArrayList<>(employees.values());
        Mockito.when(employeeDao.getGenderEmployees(gender)).thenReturn(dbOutMockEmployees);

//        when
        ArrayList<Employee> resultEmployee = employeeDao.getGenderEmployees(gender);
//        then
        assertEquals(3, resultEmployee.size());
        assertEquals("Male", resultEmployee.get(0).getGender());
        verify(employeeDao, times(1)).getGenderEmployees(Mockito.any(String.class));

    }
}