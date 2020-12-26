package com.korbiak.api.dto.input;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.TypeDto;
import lombok.Data;

@Data
public class InputItemDto {

    private String name;
    private String description;
    private int price;
    private TypeDto type;
    private BrandDto brand;
}

