package com.estock.estockmanager.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.TimeSeries;

import java.time.Instant;

@TimeSeries(collection="StockInfo", timeField = "timestamp", metaField = "companyCode")
@Data
public class Stock {
    private String companyCode;
    private Instant timestamp;
    private Double stockPrice;

}
