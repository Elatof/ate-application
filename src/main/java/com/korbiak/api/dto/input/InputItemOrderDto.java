package com.korbiak.api.dto.input;

import com.korbiak.api.dto.CustomerDto;
import com.korbiak.api.dto.EmployeeDto;
import com.korbiak.api.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputItemOrderDto {

    @NotBlank
    private Date endDate;

    @NotBlank
    private EmployeeDto employee;

    @NotBlank
    private ItemDto item;

    @NotBlank
    private CustomerDto customer;
}
