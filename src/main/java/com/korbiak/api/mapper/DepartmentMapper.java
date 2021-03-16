package com.korbiak.api.mapper;

import com.korbiak.api.dto.DepartmentDto;
import com.korbiak.api.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto getDtoFromModel(Department department);

    Department getModelFromDto(DepartmentDto departmentDto);
}
