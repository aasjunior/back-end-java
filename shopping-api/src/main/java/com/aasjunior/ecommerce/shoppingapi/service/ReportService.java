package com.aasjunior.ecommerce.shoppingapi.service;

import com.aasjunior.ecommerce.shoppingapi.dto.ShopDTO;
import com.aasjunior.ecommerce.shoppingapi.dto.ShopReportDTO;
import com.aasjunior.ecommerce.shoppingapi.model.Shop;
import com.aasjunior.ecommerce.shoppingapi.repository.ReportRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepositoryImpl reportRepository;

    public List<ShopDTO> getShopsByFilter(
        LocalDate dataInicio,
        LocalDate dataFim,
        Float valorMinimo
    ){
        List<Shop> shops = reportRepository.getShopByFilters(
            dataInicio,
            dataFim,
            valorMinimo
        );
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(
        LocalDate dataInicio,
        LocalDate dataFim
    ){
        return reportRepository
                .getReportByDate(dataInicio, dataFim);
    }
}
