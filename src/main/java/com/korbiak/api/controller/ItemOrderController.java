package com.korbiak.api.controller;

import com.korbiak.api.dto.ItemOrderDto;
import com.korbiak.api.dto.input.InputItemOrderDto;
import com.korbiak.api.service.ItemOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ate-api/orders/")
@RequiredArgsConstructor
@CrossOrigin
public class ItemOrderController {

    private final ItemOrderService itemOrderService;

//    @GetMapping
//    public List<ItemOrderDto> getAllOrders() {
//        return itemOrderService.getAllOrders();
//    }

    @GetMapping
    public List<ItemOrderDto> getAllDepartmentOrders() {
        return itemOrderService.getAllDepartmentOrders();
    }

    @GetMapping("{orderId}")
    public ItemOrderDto getOrderById(@PathVariable int orderId) {
        return itemOrderService.getOrderById(orderId);
    }

    @PostMapping
    public ItemOrderDto saveNewOrder(@RequestBody @Validated InputItemOrderDto inputItemOrderDto) {
        return itemOrderService.saveNewOrder(inputItemOrderDto);
    }

    @PutMapping
    public ItemOrderDto updateItem(@RequestBody @Validated ItemOrderDto itemOrderDto) {
        return itemOrderService.updateOrder(itemOrderDto);
    }

    @DeleteMapping("{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        itemOrderService.deleteOrder(orderId);
    }
}
