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
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getOne(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        if (employee.getId() != null && employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }

    public boolean deleteEmployeeById(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteEmployee(Employee employee) {
        if (employee.getId() != null && employeeRepository.existsById(employee.getId())) {
            employeeRepository.delete(employee);
            return true;
        } else {
            return false;
        }
    }
}
