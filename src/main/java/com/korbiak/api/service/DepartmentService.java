package com.korbiak.api.service;

import com.korbiak.api.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(int id);

}
