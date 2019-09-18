package com.antonio.employeeapi.service;

import com.antonio.employeeapi.repository.EmployeeRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


}
