package com.korbiak.api.controller;

import com.korbiak.api.dto.CustomerDto;
import com.korbiak.api.dto.TypeDto;
import com.korbiak.api.dto.input.InputCustomerDto;
import com.korbiak.api.dto.input.InputTypeDto;
import com.korbiak.api.service.CustomerService;
import com.korbiak.api.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public CustomerDto saveNewCustomer(@RequestBody @Validated InputCustomerDto customerDto) {
        return customerService.saveNewCustomer(customerDto);
    }

    @PutMapping
    public CustomerDto updateCustomer(@RequestBody @Validated CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);
    }
}
