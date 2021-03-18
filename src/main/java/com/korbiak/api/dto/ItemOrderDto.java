package com.korbiak.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderDto {

    private int id;

    private Date startDate;

    private Date endDate;

    private int price;

    private boolean closed;

    private EmployeeDto employee;

    private ItemDto item;

    private CustomerDto customer;
}
