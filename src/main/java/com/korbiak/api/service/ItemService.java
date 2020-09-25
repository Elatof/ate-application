package com.korbiak.api.service;

import com.korbiak.api.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAllItemsByDepartmentId(int departmentId);
}
