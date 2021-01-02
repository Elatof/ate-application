package com.korbiak.api.service.impl;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.mapper.BrandMapper;
import com.korbiak.api.mapper.ItemMapper;
import com.korbiak.api.model.Brand;
import com.korbiak.api.repo.BrandRepo;
import com.korbiak.api.repo.ItemRepo;
import com.korbiak.api.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepo brandRepo;
    private final BrandMapper brandMapper;

    @Override
    public List<BrandDto> getAllBrands() {
        List<Brand> brands = brandRepo.findAll();

        return brands.stream()
                .map(brandMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public BrandDto saveNewBrand(InputBrandDto brandDto) {
        Brand newBrand = brandMapper.getModelFromDto(brandDto);
        brandRepo.save(newBrand);

        return brandMapper.getDtoFromModel(newBrand);
    }

    @Override
    public void deleteBrand(int brandId) {
        brandRepo.deleteById(brandId);
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        Brand brand = brandMapper.getModelFromDto(brandDto);
        brandRepo.save(brand);

        return brandMapper.getDtoFromModel(brand);
    }
}
