package com.korbiak.api.dto;

import com.korbiak.api.model.Brand;
import com.korbiak.api.model.Department;
import com.korbiak.api.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private int price;

    @NotNull
    private TypeDto type;

    @NotNull
    private BrandDto brand;

    private String imageUrl;
}

