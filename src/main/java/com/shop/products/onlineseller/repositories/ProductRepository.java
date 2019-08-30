package com.shop.products.onlineseller.repositories;

import com.shop.products.onlineseller.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByid(Integer id);
    List<Product> findBycategory(String category);
}
