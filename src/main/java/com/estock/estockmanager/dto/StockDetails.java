package com.estock.estockmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class StockDetails {

    private String companyCode;
    private Instant startDate;
    private Instant endDate;
    private Double stockPrice;
    private Double minStockPrice;
    private Double maxStockPrice;
    private Double averageStockPrice;
}
