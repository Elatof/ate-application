package com.korbiak.api.dto.input;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.TypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputItemDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private int price;

    @NotNull
    private int commonPrice;

    @NotBlank
    private String state;

    @NotNull
    private TypeDto type;

    @NotNull
    private BrandDto brand;
}

