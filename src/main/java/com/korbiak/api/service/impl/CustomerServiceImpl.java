package com.korbiak.api.service.impl;

import com.korbiak.api.dto.CustomerDto;
import com.korbiak.api.dto.input.InputCustomerDto;
import com.korbiak.api.mapper.CustomerMapper;
import com.korbiak.api.model.Customer;
import com.korbiak.api.repo.CustomerRepo;
import com.korbiak.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();

        return customers.stream()
                .map(customerMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto saveNewCustomer(InputCustomerDto customerDto) {
        Customer customer = customerMapper.getModelFromDto(customerDto);

        customerRepo.save(customer);
        return customerMapper.getDtoFromModel(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        customerRepo.deleteById(customerId);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.getModelFromDto(customerDto);

        customerRepo.save(customer);
        return customerMapper.getDtoFromModel(customer);
    }
}
