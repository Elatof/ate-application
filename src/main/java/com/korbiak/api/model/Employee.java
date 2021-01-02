package com.korbiak.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_admin")
    private int isAdmin;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
