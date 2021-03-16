package com.korbiak.api.controller;

import com.korbiak.api.dto.CustomerDto;
import com.korbiak.api.dto.DepartmentDto;
import com.korbiak.api.dto.input.InputCustomerDto;
import com.korbiak.api.service.CustomerService;
import com.korbiak.api.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ate-api/departments/")
@RequiredArgsConstructor
@CrossOrigin
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("{departmentId}")
    public DepartmentDto getDepartmentById(@PathVariable int departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }
}
