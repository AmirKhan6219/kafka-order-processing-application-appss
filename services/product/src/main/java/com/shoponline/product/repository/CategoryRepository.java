package com.shoponline.product.repository;

import com.shoponline.product.entity.Category;
import com.shoponline.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
