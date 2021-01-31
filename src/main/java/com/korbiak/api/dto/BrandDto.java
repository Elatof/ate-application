package com.korbiak.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String urlImg;
}
