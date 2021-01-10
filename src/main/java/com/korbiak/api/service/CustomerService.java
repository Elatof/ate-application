package com.korbiak.api.service;

import com.korbiak.api.dto.BrandDto;
import com.korbiak.api.dto.CustomerDto;
import com.korbiak.api.dto.input.InputBrandDto;
import com.korbiak.api.dto.input.InputCustomerDto;
import com.korbiak.api.model.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto saveNewCustomer(InputCustomerDto customerDto);

    void deleteCustomer(int customerId);

    CustomerDto updateCustomer(CustomerDto customerDto);
}
