package com.korbiak.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "brand")
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String urlImg;
}
