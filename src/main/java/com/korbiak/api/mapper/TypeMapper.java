package com.korbiak.api.mapper;

import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputItemDto;
import com.korbiak.api.dto.input.InputTypeDto;
import com.korbiak.api.model.Type;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    TypeDto getDtoFromModel(Type type);

    Type getModelFromDto(InputTypeDto typeDto);

    Type getModelFromDto(TypeDto typeDto);
}
