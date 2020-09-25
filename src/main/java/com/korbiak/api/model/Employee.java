package com.korbiak.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
