package com.korbiak.api.dto.input;

import com.korbiak.api.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputEmployeeDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    @NotBlank
    private String password;

    @NotNull
    private int isAdmin;

    private Department department;
}
