package com.aasjunior.ecommerce.productapi.repository;

import com.aasjunior.ecommerce.productapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
