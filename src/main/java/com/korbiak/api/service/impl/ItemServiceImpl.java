package com.korbiak.api.service.impl;

import com.korbiak.api.dto.ItemDto;
import com.korbiak.api.dto.input.InputItemDto;
import com.korbiak.api.mapper.ItemMapper;
import com.korbiak.api.model.Department;
import com.korbiak.api.model.Item;
import com.korbiak.api.repo.ItemRepo;
import com.korbiak.api.service.ItemService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepo itemRepo;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> getAllItemsByDepartmentId(int departmentId) {
        List<Item> items = itemRepo.findAll();

        return items.stream()
                .filter(item -> item.getDepartment().getId() == departmentId)
                .map(itemMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto saveNewItem(InputItemDto itemDto, int departmentId) {
        Item item = itemMapper.getModelFromDto(itemDto);
        item.setDepartment(Department.builder()
                .id(departmentId)
                .build());
        itemRepo.save(item);

        return itemMapper.getDtoFromModel(item);
    }

    public ItemDto updateItem(ItemDto itemDto) {
        Item currentItem = itemRepo.findById(itemDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        Item updatedItem = itemMapper.getModelFromDto(itemDto);
        updatedItem.setDepartment(currentItem.getDepartment());
        itemRepo.save(updatedItem);

        return itemMapper.getDtoFromModel(updatedItem);
    }

    @Override
    public void deleteItem(int itemId) {
        itemRepo.deleteById(itemId);
    }
}
