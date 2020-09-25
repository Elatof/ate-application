package com.korbiak.api.dto;

import com.korbiak.api.model.Brand;
import com.korbiak.api.model.Department;
import com.korbiak.api.model.Type;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ItemDto {

    private int id;
    private String name;
    private String description;
    private int price;
    private TypeDto type;
    private BrandDto brand;
}

