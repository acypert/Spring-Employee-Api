package com.antonio.employeeapi.controller;

import com.antonio.employeeapi.entity.Employee;
import com.antonio.employeeapi.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    List<Employee> employees = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 10; i ++) {
            employees.add(new Employee((i + 1) * 100000000, "Name " + i));
        }

        employeeRepository.saveAll(employees);
    }

    @Test
    public void createEmployeeTest() throws Exception {
        mvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
                .content("{\"ssn\":451629857, \"name\":\"Lucky the Dog\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").isString());
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {
        mvc.perform(get("/employees/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        mvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[4].id").value(5));
    }

    @Test
    public void updateEmployeeTest() throws Exception {
        String jsonBody = "{\"id\":1,\"ssn\":112449857, \"name\":\"Kitty the Cat\"}";

        mvc.perform(put("/employees").contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.ssn").value(112449857))
                .andExpect(jsonPath("$.name").value("Kitty the Cat"));
    }

    @Test
    public void deleteEmployeeByIdTest() throws Exception {
        mvc.perform(delete("/employees/3"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteEmployeeByIdTestDoesntExist() throws Exception {
        mvc.perform(delete("/employees/15"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        String jsonBody = "{\"id\":1,\"ssn\":100000000,\"name\":\"Name 0\"}";

        mvc.perform(delete("/employees").contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteEmployeeTestDoesntExist() throws Exception {
        String jsonBody = "{\"id\":15,\"ssn\":150000000,\"name\":\"Name 0\"}";

        mvc.perform(delete("/employees").contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isBadRequest());
    }
}
