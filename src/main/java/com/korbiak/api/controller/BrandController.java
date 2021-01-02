package com.korbiak.api.controller;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.dto.input.InputTypeDto;
import com.korbiak.api.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brands/")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    public BrandDto saveNewBrand(@RequestBody InputBrandDto brandDto) {
        return brandService.saveNewBrand(brandDto);
    }

    @PutMapping
    public BrandDto updateBrand(@RequestBody BrandDto brandDto) {
        return brandService.updateBrand(brandDto);
    }

    @DeleteMapping("{brandId}")
    public void deleteBrand(@PathVariable int brandId) {
        brandService.deleteBrand(brandId);
    }
}
