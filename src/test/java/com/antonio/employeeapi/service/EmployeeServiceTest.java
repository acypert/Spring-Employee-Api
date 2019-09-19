package com.antonio.employeeapi.service;

import com.antonio.employeeapi.entity.Employee;
import com.antonio.employeeapi.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class EmployeeServiceTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void createEmployeeTest() {
        Employee employee = new Employee(412779654, "Antonio Chunk");
        employee = employeeService.createEmployee(employee);

        assertNotNull(employee);
        assertNotNull(employee.getId());
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee employee = new Employee(414529654, "Emily Mood");
        employeeRepository.save(employee);

        Employee svcEmployee = employeeService.getEmployeeById(employee.getId());

        assertNotNull(svcEmployee);
        assertNotNull(svcEmployee.getId());
        assertEquals("Emily Mood", svcEmployee.getName());
    }

    @Test
    public void getAllEmployeesTest() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            employees.add(new Employee((i + 1) * 100000000, "Name " + i));
        }

        employeeRepository.saveAll(employees);

        List<Employee> svcEmployees = employeeService.getAllEmployees();

        for (Employee employee : svcEmployees) {
            assertNotNull(employee);
            assertNotNull(employee.getId());
        }
    }


    @Test
    public void updateEmployeeTest() {
        Employee employeeOriginal = new Employee(777451236, "Justin Brown");
        employeeRepository.save(employeeOriginal);
        Long employeeId = employeeOriginal.getId();

        employeeOriginal.setName("Justin Bleach");
        Employee svcEmployee = employeeService.updateEmployee(employeeOriginal);

        assertEquals("Justin Bleach", svcEmployee.getName());
        assertEquals(employeeId, svcEmployee.getId());
        assertEquals(employeeOriginal.getSsn(), svcEmployee.getSsn());
    }

    @Test
    public void updateEmployeeTestDoesntExist() {
        Employee employeeOriginal = new Employee(777451236, "Justin Brown");
        employeeRepository.save(employeeOriginal);

        Employee employeeUpdated = new Employee(777451236, "Justin Bleach");
        Employee svcEmployee = employeeService.updateEmployee(employeeUpdated);

        assertNull(svcEmployee);
        employeeUpdated.setId(99L);

        svcEmployee = employeeService.updateEmployee(employeeUpdated);
        assertNull(svcEmployee);
    }

    @Test
    public void deleteEmployeeByIdTest() {
        Employee employeeOriginal = new Employee(747859632, "Travis Winston");
        employeeRepository.save(employeeOriginal);

        boolean deleted = employeeService.deleteEmployeeById(employeeOriginal.getId());
        assertTrue(deleted);

        Optional<Employee> employeeAfterDelete = employeeRepository.findById(employeeOriginal.getId());
        assertFalse(employeeAfterDelete.isPresent());
    }

    @Test
    public void deleteEmployeeTest() {
        Employee employeeOriginal = new Employee(444125487, "Matt Shellstak");
        employeeRepository.save(employeeOriginal);

        boolean deleted = employeeService.deleteEmployee(employeeOriginal);
        assertTrue(deleted);

        Optional<Employee> employeeAfterDelete = employeeRepository.findById(employeeOriginal.getId());
        assertFalse(employeeAfterDelete.isPresent());
    }
}
