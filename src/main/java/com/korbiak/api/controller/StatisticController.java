package com.korbiak.api.controller;

import com.korbiak.api.dto.StatisticDto;
import com.korbiak.api.dto.input.InputStatisticDto;
import com.korbiak.api.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("ate-api/statistic/")
@RequiredArgsConstructor
@CrossOrigin
public class StatisticController {

    private final StatisticService service;

    @PostMapping
    public StatisticDto getStat(@Valid @RequestBody InputStatisticDto statisticDto) {
        return service.getCommonStat(statisticDto.getDepartmentDto(), statisticDto.getStartDate(), statisticDto.getEndDate());
    }
}
