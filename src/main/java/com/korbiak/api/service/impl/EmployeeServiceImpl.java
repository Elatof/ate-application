package com.korbiak.api.service.impl;

import com.korbiak.api.model.Employee;
import com.korbiak.api.repo.EmployeeRepository;
import com.korbiak.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee findUserByName(String firstName, String secondName) {
        return employeeRepository.findUserByFirstNameAndSecondName(firstName, secondName)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with name " + firstName));
    }
}
