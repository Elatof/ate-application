package com.korbiak.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {

    private int numberOfOrders;
    private Map<String, Integer> departmentsNumber;
    private Map<String, Integer> employeeNumber;
    private Map<String, Integer> typeItemNumber;

    private int commonProfit;
    private Map<String, Integer> departmentsProfit;
    private Map<String, Integer> employeeProfit;
    private Map<String, Integer> typeItemProfit;

    private Map<Date, Integer> dateOrders;
    private Map<Date, Integer> dateProfit;
}
