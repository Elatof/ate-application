package com.korbiak.api.service.impl;

import com.korbiak.api.dto.DepartmentDto;
import com.korbiak.api.mapper.DepartmentMapper;
import com.korbiak.api.mapper.EmployeeMapper;
import com.korbiak.api.model.Department;
import com.korbiak.api.repo.DepartmentRepo;
import com.korbiak.api.repo.EmployeeRepository;
import com.korbiak.api.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepo.findAll().stream()
                .map(departmentMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(int id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        return departmentMapper.getDtoFromModel(department);
    }
}
