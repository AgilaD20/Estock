package com.estock.estockmanager.repository;


import com.estock.estockmanager.Entity.Stock;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface StockInfoRepository {

    Stock findLatestStock(String companyCode);

    List<Stock> retrieveStockDetails(String companyCode, Instant startDate, Instant endDate);

    void deleteAllByCompanyCode(String companyCode);

    Stock save(Stock stock);
}
