package com.korbiak.api.mapper;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto getDtoFromModel(Brand brand);

    Brand getModelFromDto(InputBrandDto brandDto);

    Brand getModelFromDto(BrandDto brandDto);
}
