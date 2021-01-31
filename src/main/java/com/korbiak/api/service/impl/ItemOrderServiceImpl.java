package com.korbiak.api.service.impl;

import com.korbiak.api.dto.ItemOrderDto;
import com.korbiak.api.dto.input.InputItemOrderDto;
import com.korbiak.api.mapper.ItemOrderMapper;
import com.korbiak.api.model.ItemOrder;
import com.korbiak.api.repo.ItemOrderRepo;
import com.korbiak.api.service.ItemOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemOrderServiceImpl implements ItemOrderService {

    private final ItemOrderRepo itemOrderRepo;
    private final ItemOrderMapper itemOrderMapper;


    @Override
    public List<ItemOrderDto> getAllOrders() {
        return itemOrderRepo.findAll().stream()
                .map(itemOrderMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public ItemOrderDto saveNewOrder(InputItemOrderDto inputItemOrderDto) {
        ItemOrder itemOrder = itemOrderMapper.getModelFromDto(inputItemOrderDto);
        itemOrder.setStartDate(new Date());
        itemOrderRepo.save(itemOrder);
        return itemOrderMapper.getDtoFromModel(itemOrder);
    }

    @Override
    public void deleteOrder(int orderId) {
        itemOrderRepo.deleteById(orderId);
    }

    @Override
    public ItemOrderDto updateOrder(ItemOrderDto itemOrderDto) {
        ItemOrder itemOrder = itemOrderMapper.getModelFromDto(itemOrderDto);
        itemOrderRepo.save(itemOrder);
        return itemOrderMapper.getDtoFromModel(itemOrder);
    }
}
