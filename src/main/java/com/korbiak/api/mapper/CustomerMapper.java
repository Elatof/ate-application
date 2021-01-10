package com.korbiak.api.mapper;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.CustomerDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.dto.input.InputCustomerDto;
import com.korbiak.api.model.Brand;
import com.korbiak.api.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto getDtoFromModel(Customer customer);

    Customer getModelFromDto(InputCustomerDto customerDto);

    Customer getModelFromDto(CustomerDto customerDto);
}
