package com.korbiak.api.service;


import com.korbiak.api.model.Employee;

public interface EmployeeService {

    Employee findUserByName(String firstName, String secondName);
}
