package com.korbiak.api.mapper;

import com.korbiak.api.dto.ItemDto;
import com.korbiak.api.model.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto getDtoFromModel(Item item);
}
