package com.korbiak.api.controller;

import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputTypeDto;
import com.korbiak.api.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("types/")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    public List<TypeDto> getAllTypes() {
        return typeService.getAllTypes();
    }

    @PostMapping
    public TypeDto saveNewItem(@RequestBody @Validated InputTypeDto typeDto) {
        return typeService.saveNewType(typeDto);
    }

    @PutMapping
    public TypeDto updateItem(@RequestBody @Validated TypeDto typeDto) {
        return typeService.updateType(typeDto);
    }

    @DeleteMapping("{typeId}")
    public void deleteItem(@PathVariable int typeId) {
        typeService.deleteType(typeId);
    }
}
