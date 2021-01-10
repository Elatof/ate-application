package com.korbiak.api.repo;

import com.korbiak.api.model.Brand;
import com.korbiak.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
