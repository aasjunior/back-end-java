package com.aasjunior.ecommerce.service;

import com.aasjunior.ecommerce.converter.DTOConverter;
import com.aasjunior.ecommerce.shoppingclient.dto.ShopDTO;
import com.aasjunior.ecommerce.shoppingclient.dto.ShopReportDTO;
import com.aasjunior.ecommerce.model.Shop;
import com.aasjunior.ecommerce.repository.ReportRepositoryImpl;
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
                .map(DTOConverter::convert)
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
