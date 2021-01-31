package com.korbiak.api.service;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.ItemOrderDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.dto.input.InputItemOrderDto;
import com.korbiak.api.model.ItemOrder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemOrderService {

    List<ItemOrderDto> getAllOrders();

    ItemOrderDto saveNewOrder(InputItemOrderDto inputItemOrderDto);

    void deleteOrder(int orderId);

    ItemOrderDto updateOrder(ItemOrderDto itemOrderDto);
}
