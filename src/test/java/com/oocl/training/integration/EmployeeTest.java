package com.oocl.training.integration;

import com.oocl.training.dao.EmployeeDbDao;
import com.oocl.training.model.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class EmployeeTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private EmployeeDbDao employeeDbDao;

    @BeforeAll
    public void setup () {
//        employeeDbDao.save(new Employee("John", 25, "Male", 30000, true,1));
//        employeeDbDao.save(new Employee("John2", 25, "Female", 30000, true,2));
//        employeeDbDao.save(new Employee("John3", 25, "Male", 30000, true,1));
//        employeeDbDao.save(new Employee("John4", 25, "Female", 30000, true,2));
//        employeeDbDao.save(new Employee("John5", 20, "Male", 3000, true,1));
        employeeDbDao.save(new Employee("John", 25, "Male", 30000, true));
        employeeDbDao.save(new Employee("John2", 25, "Female", 30000, true));
        employeeDbDao.save(new Employee("John3", 25, "Male", 30000, true));
        employeeDbDao.save(new Employee("John4", 25, "Female", 30000, true));
        employeeDbDao.save(new Employee("John5", 20, "Male", 3000, true));
    }

    @AfterAll
    public void tearDown() {
        // 删除上述添加的员工
        employeeDbDao.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_all_given_no_filter() throws Exception {
//    given
        List<Employee> expectedEmployees = employeeDbDao.getAllEmployees();
//    when
        ResultActions result = client.perform(MockMvcRequestBuilders.get("/employees"));
//        then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(expectedEmployees.get(0).getId()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(expectedEmployees.get(0).getName()));

    }

    @Test
    public void should_return_employees_by_gender() throws Exception{
//        given
        List<Employee> expectedEmployees = employeeDbDao.getEmployeesByGender("Male");
//        when
        ResultActions result = client.perform(MockMvcRequestBuilders.get("/employees?gender=Male"));
//        then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(expectedEmployees.size()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(expectedEmployees.get(0).getId()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(expectedEmployees.get(0).getName()));
    }

    @Test
    public void should_return_employee_by_id() throws Exception{
//        given
//        Employee expectedEmployee = employeeDao.getEmployeeById(12);
//        when
        ResultActions result = client.perform(MockMvcRequestBuilders.get("/employees/12"));
//        then
        result.andExpect(MockMvcResultMatchers.status().is4xxClientError());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Employee not found with id: 12"));
    }

}
