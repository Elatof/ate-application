package com.korbiak.api.controller;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.EmployeeDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.dto.input.InputEmployeeDto;
import com.korbiak.api.service.BrandService;
import com.korbiak.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("ate-api/employees/")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "users")
    public List<EmployeeDto> getAllUsers() {
        return employeeService.getAllUsers();
    }

    @GetMapping(path = "admins")
    public List<EmployeeDto> getAllAdmins() {
        return employeeService.getAllAdmins();
    }

    @PostMapping
    public EmployeeDto saveNewEmployee(@RequestBody @Validated InputEmployeeDto employeeDto) {
        return employeeService.saveNewEmployee(employeeDto);
    }

    @PutMapping
    public EmployeeDto updateEmployee(@RequestBody @Validated EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

    @DeleteMapping("{employeeId}")
    public void deleteBrand(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
