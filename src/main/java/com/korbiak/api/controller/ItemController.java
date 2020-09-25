package com.korbiak.api.controller;

import com.korbiak.api.dto.ItemDto;
import com.korbiak.api.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("items/")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItemsInDepartment(@RequestParam int departmentId) {
        return itemService.getAllItemsByDepartmentId(departmentId);
    }
}
