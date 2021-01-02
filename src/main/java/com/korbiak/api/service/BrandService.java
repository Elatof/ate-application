package com.korbiak.api.service;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.dto.input.InputTypeDto;

import java.util.List;

public interface BrandService {

    List<BrandDto> getAllBrands();

    BrandDto saveNewBrand(InputBrandDto brandDto);

    void deleteBrand(int brandId);

    BrandDto updateBrand(BrandDto brandDto);
}
