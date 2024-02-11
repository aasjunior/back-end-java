package com.aasjunior.ecommerce.repository;

import com.aasjunior.ecommerce.dto.ShopReportDTO;
import com.aasjunior.ecommerce.model.Shop;

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
