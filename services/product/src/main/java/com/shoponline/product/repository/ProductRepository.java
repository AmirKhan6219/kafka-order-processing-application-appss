package com.shoponline.product.repository;

import com.shoponline.product.dto.ProductDto;
import com.shoponline.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdInOrderById(List<Long> productId);
    Optional<List<ProductDto>> findByName(String name);
}
