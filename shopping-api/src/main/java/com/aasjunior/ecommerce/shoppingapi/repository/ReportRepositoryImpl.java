package com.aasjunior.ecommerce.shoppingapi.repository;

import com.aasjunior.ecommerce.shoppingapi.dto.ShopReportDTO;
import com.aasjunior.ecommerce.shoppingapi.model.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
@Repository
public class ReportRepositoryImpl implements ReportRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT s");
        sb.append("FROM shop s");
        sb.append("WHERE s.date >= :dataInicio");

        if(dataFim != null){
            sb.append("AND s.date <= :dataFim");
        }
        if(valorMinimo != null){
            sb.append("AND s.total <= :valorMinimo");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("dataInicio", dataInicio.atTime(0, 0));

        if(dataFim != null){
            query.setParameter("dataFim", dataFim.atTime(23,59));
        }
        if(valorMinimo != null){
            query.setParameter("valorMinimo", valorMinimo);
        }
        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim){
        StringBuilder sb = new StringBuilder();
        sb.append(
            """
            SELECT COUNT(sp.id),
                   SUM(sp.total),
                   AVG(sp.total)        
            """
        );
        sb.append("FROM shopping.shop sp");
        sb.append("WHERE sp.date >= :dataInicio");
        sb.append("AND sp.date <= :dataFim");

        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("dataInicio", dataInicio.atTime(0, 0));
        query.setParameter("dataFim", dataFim.atTime(23,59));
        Object[] result = (Object[]) query.getSingleResult();

        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((BigInteger) result[0]).intValue());
        shopReportDTO.setTotal((Double) result[1]);
        shopReportDTO.setMean((Double) result[2]);
        return shopReportDTO;
    }
}
