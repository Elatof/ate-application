package com.korbiak.api.repo;

import com.korbiak.api.model.Employee;
import com.korbiak.api.model.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepo extends JpaRepository<ItemOrder, Integer> {
}
