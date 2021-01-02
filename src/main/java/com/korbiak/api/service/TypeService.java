package com.korbiak.api.service;

import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputTypeDto;

import java.util.List;

public interface TypeService {

    List<TypeDto> getAllTypes();

    TypeDto saveNewType(InputTypeDto typeDto);

    void deleteType(int typeId);

    TypeDto updateType(TypeDto typeDto);
}
