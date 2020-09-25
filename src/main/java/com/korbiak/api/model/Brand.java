package com.korbiak.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
@Data
public class Brand {
    @Id
    private int id;

    @Column(name = "name")
    private String name;
}
