package com.korbiak.api.dto;

import com.korbiak.api.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private int id;

    private String firstName;

    private String secondName;

    private int isAdmin;

    private Department department;
}
