package com.korbiak.api.controller;

import com.korbiak.api.dto.ItemDto;
import com.korbiak.api.dto.input.InputItemDto;
import com.korbiak.api.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ate-api/items/")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItemsInDepartment(@RequestParam @Validated int departmentId) {
        return itemService.getAllItemsByDepartmentId(departmentId);
    }

    @PostMapping
    public ItemDto saveNewItem(@RequestBody @Validated InputItemDto itemDto, int departmentId) {
        return itemService.saveNewItem(itemDto, departmentId);
    }

    @PutMapping
    public ItemDto updateItem(@RequestBody @Validated ItemDto itemDto) {
        return itemService.updateItem(itemDto);
    }

    @DeleteMapping("{itemId}")
    public void deleteItem(@PathVariable int itemId) {
        itemService.deleteItem(itemId);
    }
}
