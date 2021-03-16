package com.korbiak.api.service.impl;

import com.korbiak.api.dto.DepartmentDto;
import com.korbiak.api.dto.StatisticDto;
import com.korbiak.api.model.ItemOrder;
import com.korbiak.api.repo.ItemOrderRepo;
import com.korbiak.api.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final ItemOrderRepo itemOrderRepo;

    @Override
    public StatisticDto getCommonStat(DepartmentDto departmentDto, Date startDate, Date endDate) {
        List<ItemOrder> orderList = itemOrderRepo.findAll();
        if (departmentDto != null) {
            orderList = orderList.stream().filter(itemOrder -> itemOrder.getItem().getDepartment().getId()
                    == departmentDto.getId()).collect(Collectors.toList());
        }

        orderList = orderList.stream().filter(itemOrder -> itemOrder.getEndDate().after(startDate)
                && itemOrder.getEndDate().before(endDate))
                .collect(Collectors.toList());

        int numberOfOrders = orderList.size();
        Map<String, Integer> departmentsNumber = new HashMap<>();
        Map<String, Integer> employeeNumber = new HashMap<>();
        Map<String, Integer> typeItemNumber = new HashMap<>();

        int commonProfit = 0;

        Map<String, Integer> departmentsProfit = new HashMap<>();
        Map<String, Integer> employeeProfit = new HashMap<>();
        Map<String, Integer> typeItemProfit = new HashMap<>();

        for (ItemOrder order : orderList) {
            String departmentName = order.getItem().getDepartment().getName();
            String employeeName = order.getEmployee().getFirstName() + " " + order.getEmployee().getSecondName();
            String typeName = order.getItem().getType().getName();

            departmentsNumber.merge(departmentName, 1, Integer::sum);
            employeeNumber.merge(employeeName, 1, Integer::sum);
            typeItemNumber.merge(typeName, 1, Integer::sum);

            int currentProfit = (int) (order.getItem().getPrice() * getDifferenceDays(order.getStartDate(), order.getEndDate()));
            commonProfit += currentProfit;

            departmentsProfit.merge(departmentName, currentProfit, Integer::sum);
            employeeProfit.merge(employeeName, currentProfit, Integer::sum);
            typeItemProfit.merge(typeName, currentProfit, Integer::sum);
        }

        Map<Date, Integer> dateOrders = new TreeMap<>();
        Map<Date, Integer> dateProfit = new TreeMap<>();
        List<Date> dates = getInterval(startDate, endDate);

        for (Date date : dates) {
            dateOrders.put(date, 0);
            dateProfit.put(date, 0);
            for (ItemOrder order : orderList) {
                if (equalsMonth(date, order.getEndDate())) {
                    dateOrders.merge(date, 1, Integer::sum);
                    dateProfit.merge(date, (int) (order.getItem().getPrice() * getDifferenceDays(order.getStartDate(),
                            order.getEndDate())), Integer::sum);
                }
            }
        }

        return new StatisticDto(numberOfOrders, departmentsNumber, employeeNumber, typeItemNumber, commonProfit, departmentsProfit,
                employeeProfit, typeItemProfit, dateOrders, dateProfit);
    }

    public long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
    }

    public List<Date> getInterval(Date start, Date end) {
        List<Date> dateIntegerMap = new ArrayList<>();
        dateIntegerMap.add(start);
        Date next = DateUtils.addMonths(start, 1);
        while (next.before(end) || next.equals(end)) {
            dateIntegerMap.add(next);
            next = DateUtils.addMonths(next, 1);
        }
        return dateIntegerMap;
    }

    public boolean equalsMonth(Date d1, Date d2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
        return fmt.format(d1).equals(fmt.format(d2));
    }
}
