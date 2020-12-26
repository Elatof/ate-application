package com.korbiak.api.service;

import com.korbiak.api.dto.ItemDto;
import com.korbiak.api.dto.input.InputItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAllItemsByDepartmentId(int departmentId);

    ItemDto saveNewItem(InputItemDto itemDto);
}
