package com.korbiak.api.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "department")
@Data
public class Department {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id")
    private Address address;
}
