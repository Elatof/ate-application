package com.korbiak.api.service;

import com.korbiak.api.dto.DepartmentDto;
import com.korbiak.api.dto.StatisticDto;

import java.util.Date;

public interface StatisticService {

    StatisticDto getCommonStat(DepartmentDto departmentDto, Date startDate, Date endDate);
}
