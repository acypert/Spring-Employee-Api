package com.antonio.employeeapi.service;

import com.antonio.employeeapi.entity.Employee;
import com.antonio.employeeapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return null;
    }

    public Employee getEmployeeById(Long id) {
        return null;
    }

    public List<Employee> getAllEmployees() {
        return null;
    }

    public Employee updateEmployeeById(Long id) {
        return null;
    }

    public Employee updateEmployee(Employee employee) {
        return null;
    }

    public boolean deleteEmployeeById(Long id) {
        return false;
    }

    public boolean deleteEmployee(Employee employee) {
        return false;
    }
}
