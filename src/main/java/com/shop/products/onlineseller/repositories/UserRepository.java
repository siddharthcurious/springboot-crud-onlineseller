package com.shop.products.onlineseller.repositories;

import com.shop.products.onlineseller.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByid(Integer id);
    List<User> findBycity(String city);
}
