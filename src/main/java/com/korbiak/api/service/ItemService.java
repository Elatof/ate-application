package com.korbiak.api.service;

import com.korbiak.api.dto.ItemDto;
import com.korbiak.api.dto.input.InputItemDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAllItemsByDepartmentId(boolean all);

    ItemDto getItemsById(int id);

    ItemDto saveNewItem(InputItemDto itemDto, MultipartFile multipartFile);

    void deleteItem(int itemId);

    ItemDto updateItem(ItemDto itemDto, MultipartFile multipartFile);
}
