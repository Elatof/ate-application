package com.korbiak.api.dto.input;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.TypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputItemDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private int price;

    @NotBlank
    private TypeDto type;

    @NotBlank
    private BrandDto brand;
}

