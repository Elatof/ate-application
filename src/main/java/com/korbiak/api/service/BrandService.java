package com.korbiak.api.service;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.dto.input.InputTypeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BrandService {

    List<BrandDto> getAllBrands();

    BrandDto saveNewBrand(InputBrandDto brandDto, MultipartFile image);

    void deleteBrand(int brandId);

    BrandDto updateBrand(int brandId, InputBrandDto brandDto, MultipartFile image);
}
