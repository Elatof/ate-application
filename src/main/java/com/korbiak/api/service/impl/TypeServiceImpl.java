package com.korbiak.api.service.impl;

import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputTypeDto;
import com.korbiak.api.mapper.BrandMapper;
import com.korbiak.api.mapper.TypeMapper;
import com.korbiak.api.model.Type;
import com.korbiak.api.repo.BrandRepo;
import com.korbiak.api.repo.TypeRepo;
import com.korbiak.api.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepo typeRepo;
    private final TypeMapper typeMapper;

    @Override
    public List<TypeDto> getAllTypes() {
        List<Type> types = typeRepo.findAll();

        return types.stream()
                .map(typeMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public TypeDto saveNewType(InputTypeDto typeDto) {
        Type newType = typeMapper.getModelFromDto(typeDto);
        typeRepo.save(newType);

        return typeMapper.getDtoFromModel(newType);
    }

    @Override
    public void deleteType(int typeId) {
        typeRepo.deleteById(typeId);
    }

    @Override
    public TypeDto updateType(TypeDto typeDto) {
        Type type = typeMapper.getModelFromDto(typeDto);
        typeRepo.save(type);

        return typeMapper.getDtoFromModel(type);
    }
}
