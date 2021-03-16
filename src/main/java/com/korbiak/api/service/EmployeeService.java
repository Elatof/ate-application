package com.korbiak.api.service;


import com.korbiak.api.dto.EmployeeDto;
import com.korbiak.api.dto.input.InputEmployeeDto;
import com.korbiak.api.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findUserByName(String firstName, String secondName);

    List<EmployeeDto> getAllUsers();

    List<EmployeeDto> getAllAdmins();

    EmployeeDto saveNewEmployee(InputEmployeeDto employeeDto);

    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    void deleteEmployee(int id);
}
