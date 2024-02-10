package com.aasjunior.ecommerce.shoppingapi.repository;

import com.aasjunior.ecommerce.shoppingclient.dto.ShopReportDTO;
import com.aasjunior.ecommerce.shoppingapi.model.Shop;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {
    public List<Shop> getShopByFilters(
        LocalDate dataInicio,
        LocalDate dataFim,
        Float valorMinimo
    );

    public ShopReportDTO getReportByDate(
        LocalDate dataInicio,
        LocalDate dataFim
    );
}
