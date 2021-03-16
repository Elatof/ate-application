package com.korbiak.api.mapper;

import com.korbiak.api.dto.EmployeeDto;
import com.korbiak.api.dto.input.InputEmployeeDto;
import com.korbiak.api.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto getDtoFromModel(Employee employee);

    Employee getModelFromDto(InputEmployeeDto employeeDto);

    Employee getModelFromDto(EmployeeDto employeeDto);
}
