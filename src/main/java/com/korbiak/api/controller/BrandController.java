package com.korbiak.api.controller;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.dto.input.InputTypeDto;
import com.korbiak.api.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("ate-api/brands/")
@RequiredArgsConstructor
@CrossOrigin
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    public BrandDto saveNewBrand(@RequestParam @Validated InputBrandDto brandDto,
                                 @RequestParam(value = "file", required = false) MultipartFile image) {
        return brandService.saveNewBrand(brandDto, image);
    }

    @PutMapping(path = "{brandId}")
    public BrandDto updateBrand(@PathVariable int brandId,
                                @RequestParam @Validated InputBrandDto brandDto,
                                @RequestParam(value = "file", required = false) MultipartFile image) {
        return brandService.updateBrand(brandId, brandDto, image);
    }

    @DeleteMapping("{brandId}")
    public void deleteBrand(@PathVariable int brandId) {
        brandService.deleteBrand(brandId);
    }
}
