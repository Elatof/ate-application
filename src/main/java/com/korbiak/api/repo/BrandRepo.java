package com.korbiak.api.repo;

import com.korbiak.api.model.Brand;
import com.korbiak.api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {
    Brand findBrandByName(String name);
}

