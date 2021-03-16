package com.korbiak.api.service.impl;

import com.korbiak.api.dto.EmployeeDto;
import com.korbiak.api.dto.input.InputEmployeeDto;
import com.korbiak.api.mapper.EmployeeMapper;
import com.korbiak.api.model.Employee;
import com.korbiak.api.repo.EmployeeRepository;
import com.korbiak.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee findUserByName(String firstName, String secondName) {
        return employeeRepository.findUserByFirstNameAndSecondName(firstName, secondName)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with name " + firstName));
    }

    @Override
    public List<EmployeeDto> getAllUsers() {
        List<Employee> employees = employeeRepository.findAll().stream()
                .filter(employee -> employee.getIsAdmin() == 1)
                .collect(Collectors.toList());

        return employees.stream()
                .map(employeeMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getAllAdmins() {
        List<Employee> employees = employeeRepository.findAll().stream()
                .filter(employee -> employee.getIsAdmin() == 2)
                .collect(Collectors.toList());

        return employees.stream()
                .map(employeeMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto saveNewEmployee(InputEmployeeDto employeeDto) {
        Employee employee = employeeMapper.getModelFromDto(employeeDto);
        employeeRepository.save(employee);

        return employeeMapper.getDtoFromModel(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.getModelFromDto(employeeDto);

        employeeRepository.save(employee);

        return employeeMapper.getDtoFromModel(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
