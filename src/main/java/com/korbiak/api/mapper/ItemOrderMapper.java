package com.korbiak.api.mapper;

import com.korbiak.api.dto.ItemOrderDto;
import com.korbiak.api.dto.input.InputItemOrderDto;
import com.korbiak.api.model.ItemOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemOrderMapper {

    ItemOrderDto getDtoFromModel(ItemOrder itemOrder);

    ItemOrder getModelFromDto(InputItemOrderDto inputItemOrderDto);

    ItemOrder getModelFromDto(ItemOrderDto itemOrderDto);
}
