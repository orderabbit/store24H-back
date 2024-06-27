package com.example.back.repository;

import com.example.back.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByProductId(Long productId);
    List<CartEntity> findByUserId(String userId);
}

