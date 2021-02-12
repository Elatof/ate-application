package com.korbiak.api.controller;

import com.korbiak.api.dto.ItemDto;
import com.korbiak.api.dto.input.InputItemDto;
import com.korbiak.api.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("ate-api/items/")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItemsInDepartment() {
        return itemService.getAllItemsByDepartmentId();
    }

    @PostMapping
    public ItemDto saveNewItem(@Validated InputItemDto itemDto,
                               @RequestParam(value = "file", required = false) MultipartFile image) {
        return itemService.saveNewItem(itemDto, image);
    }

    @PutMapping
    public ItemDto updateItem(@Validated ItemDto itemDto,
                              @RequestParam(value = "file", required = false) MultipartFile image) {
        return itemService.updateItem(itemDto, image);
    }

    @DeleteMapping("{itemId}")
    public void deleteItem(@PathVariable int itemId) {
        itemService.deleteItem(itemId);
    }
}
