package com.aasjunior.ecommerce.shoppingapi.repository;

import com.aasjunior.ecommerce.shoppingapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ShopRepository extends JpaRepository<Shop, Long> {
    public List<Shop> findAllByUserIdentifier(String userIdentifier);

    public List<Shop> findAllByTotalGreaterThan(Float total);

    List<Shop> findAllByDateGreaterThan(LocalDateTime date);
}
