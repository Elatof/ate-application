package com.korbiak.api.service.impl;

import com.korbiak.api.cloudinaryapi.CloudinaryManager;
import com.korbiak.api.dto.ItemDto;
import com.korbiak.api.dto.input.InputItemDto;
import com.korbiak.api.mapper.ItemMapper;
import com.korbiak.api.model.Department;
import com.korbiak.api.model.Employee;
import com.korbiak.api.model.Item;
import com.korbiak.api.model.ItemOrder;
import com.korbiak.api.repo.EmployeeRepository;
import com.korbiak.api.repo.ItemOrderRepo;
import com.korbiak.api.repo.ItemRepo;
import com.korbiak.api.security.jwt.JwtUser;
import com.korbiak.api.service.ItemOrderService;
import com.korbiak.api.service.ItemService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepo itemRepo;
    private final ItemMapper itemMapper;
    private final CloudinaryManager cloudinaryManager;
    private final EmployeeRepository employeeRepository;
    private final ItemOrderRepo itemOrderRepo;

    @Override
    public List<ItemDto> getAllItemsByDepartmentId(boolean all) {
        List<Item> items = itemRepo.findAll();
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<ItemOrder> orders = itemOrderRepo.findAll();

        return items.stream()
                .filter(item -> item.getDepartment().getId() == employee.getId())
                .map(itemMapper::getDtoFromModel)
                .peek(itemDto -> {
                    boolean isFree = true;
                    for (ItemOrder order : orders) {
                        if (order.getItem().getId() == itemDto.getId()) {
                            isFree = false;
                            break;
                        }
                    }
                    itemDto.setFree(isFree);
                }).filter(itemDto -> {
                    if (!all) {
                        return itemDto.isFree();
                    }
                    return true;
                }).collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemsById(int id) {
        List<ItemDto> items = getAllItemsByDepartmentId(true);
        for (ItemDto item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new IllegalArgumentException("Item not found");
    }

    @Override
    public ItemDto saveNewItem(InputItemDto itemDto, MultipartFile multipartFile) {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Item item = itemMapper.getModelFromDto(itemDto);
        item.setDepartment(Department.builder()
                .id(employee.getDepartment().getId())
                .build());
        itemRepo.save(item);

        String file = null;
        if (multipartFile != null) {
            try {
                file = cloudinaryManager.uploadImage(multipartFile, "item", item.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        item.setImageUrl(file);
        itemRepo.save(item);

        return itemMapper.getDtoFromModel(item);
    }

    public ItemDto updateItem(ItemDto itemDto, MultipartFile multipartFile) {
        Item currentItem = itemRepo.findById(itemDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        Item updatedItem = itemMapper.getModelFromDto(itemDto);
        updatedItem.setDepartment(currentItem.getDepartment());
        updatedItem.setImageUrl(currentItem.getImageUrl());

        if (multipartFile != null) {
            try {
                updatedItem.setImageUrl(cloudinaryManager.uploadImage(multipartFile, "item", currentItem.getId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        itemRepo.save(updatedItem);

        return itemMapper.getDtoFromModel(updatedItem);
    }

    @Override
    public void deleteItem(int itemId) {
        itemRepo.deleteById(itemId);
    }
}
