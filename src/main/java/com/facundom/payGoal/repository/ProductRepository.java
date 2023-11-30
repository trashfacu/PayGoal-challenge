package com.facundom.payGoal.repository;

import com.facundom.payGoal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByOrderByProductPriceDesc();
}
