package com.korbiak.api.service.impl;

import com.korbiak.api.dto.ItemOrderDto;
import com.korbiak.api.dto.input.InputItemOrderDto;
import com.korbiak.api.mapper.ItemOrderMapper;
import com.korbiak.api.model.Employee;
import com.korbiak.api.model.ItemOrder;
import com.korbiak.api.repo.EmployeeRepository;
import com.korbiak.api.repo.ItemOrderRepo;
import com.korbiak.api.security.jwt.JwtUser;
import com.korbiak.api.service.ItemOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemOrderServiceImpl implements ItemOrderService {

    private final ItemOrderRepo itemOrderRepo;
    private final ItemOrderMapper itemOrderMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<ItemOrderDto> getAllOrders() {
        return itemOrderRepo.findAll().stream()
                .map(itemOrderMapper::getDtoFromModel)
                .peek(itemOrderDto -> itemOrderDto.setClosed(!itemOrderDto.getEndDate().after(new Date())))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemOrderDto> getAllDepartmentOrders() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return itemOrderRepo.findAll().stream()
                .filter(itemOrder -> employee.getDepartment().equals(itemOrder.getItem().getDepartment()))
                .map(itemOrderMapper::getDtoFromModel)
                .map(this::countPrice)
                .peek(itemOrderDto -> itemOrderDto.setClosed(!itemOrderDto.getEndDate().after(new Date())))
                .collect(Collectors.toList());
    }

    @Override
    public ItemOrderDto getOrderById(int orderId) {
        ItemOrder itemOrder = itemOrderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        ItemOrderDto itemOrderDto = countPrice(itemOrderMapper.getDtoFromModel(itemOrder));
        itemOrderDto.setClosed(!itemOrderDto.getEndDate().after(new Date()));
        return itemOrderDto;
    }

    @Override
    public ItemOrderDto saveNewOrder(InputItemOrderDto inputItemOrderDto) {
        ItemOrder itemOrder = itemOrderMapper.getModelFromDto(inputItemOrderDto);
        itemOrder.setStartDate(new Date());

        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = new Employee();
        employee.setId(user.getId());
        itemOrder.setEmployee(employee);

        itemOrderRepo.save(itemOrder);
        ItemOrderDto itemOrderDto = countPrice(itemOrderMapper.getDtoFromModel(itemOrder));
        itemOrderDto.setClosed(!itemOrderDto.getEndDate().after(new Date()));
        return itemOrderDto;
    }

    @Override
    public void deleteOrder(int orderId) {
        itemOrderRepo.deleteById(orderId);
    }

    @Override
    public ItemOrderDto updateOrder(ItemOrderDto itemOrderDto) {
        ItemOrder itemOrder = itemOrderMapper.getModelFromDto(itemOrderDto);
        itemOrderRepo.save(itemOrder);
        itemOrderDto = countPrice(itemOrderMapper.getDtoFromModel(itemOrder));
        itemOrderDto.setClosed(!itemOrderDto.getEndDate().after(new Date()));
        return itemOrderDto;
    }

    private ItemOrderDto countPrice(ItemOrderDto orderDto) {
        long diff = orderDto.getEndDate().getTime() - orderDto.getStartDate().getTime();
        int days = (int) (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1);
        orderDto.setPrice(days * orderDto.getItem().getPrice());
        return orderDto;
    }

}
