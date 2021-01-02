package com.korbiak.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "type")
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
}
