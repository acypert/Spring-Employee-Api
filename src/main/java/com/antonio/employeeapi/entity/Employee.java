package com.antonio.employeeapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ssn;
    private String name;

    public Employee() {

    }

    public Employee(int ssn, String name) {
        this.ssn = ssn;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public int getSsn() {
        return ssn;
    }

    public Employee setSsn(int ssn) {
        this.ssn = ssn;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }
}
