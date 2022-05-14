package com.estock.estockmanager.service;

import com.estock.estockmanager.Entity.Stock;
import com.estock.estockmanager.dto.StockDetails;
import com.estock.estockmanager.exception.StockNotFoundException;
import com.estock.estockmanager.repository.StockInfoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockService {

    @Autowired
    private StockInfoRepositoryImpl stockInfoRepository;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Stock addstock(Double price, String companyCode){
        Stock stock = new Stock();
        stock.setStockPrice(price);
        stock.setCompanyCode(companyCode);
        stock.setTimestamp(Instant.now());
        Stock addedStock = stockInfoRepository.save(stock);
        return addedStock;

    }


    public StockDetails getStockDetails(String companyCode, String startDate, String endDate) {
        Stock latestStock = stockInfoRepository.findLatestStock(companyCode);
        if(null!=latestStock) {
            Instant start = LocalDate.parse(startDate, dateTimeFormatter).atStartOfDay(ZoneId.of("+0")).toInstant();
            Instant end = LocalDate.parse(endDate, dateTimeFormatter).atStartOfDay(ZoneId.of("+0")).toInstant();
            List<Double> stockPriceList = stockInfoRepository.retrieveStockDetails(companyCode, start, end).stream().map(stock -> stock.getStockPrice()).collect(Collectors.toList());
            stockPriceList.stream().forEach(s->log.info(s.toString()));
            Optional<Double> min = stockPriceList.stream().min(Double::compare);
            Optional<Double> max = stockPriceList.stream().max((a,b)-> a>b?1:-1);
            Double average = stockPriceList.stream().collect(Collectors.averagingDouble(d -> d));
            Double minValue = min.orElseGet(()->0.0);
            Double maxValue = max.orElseGet(()->0.0);
            StockDetails stockDetails = new StockDetails(companyCode, start, end,latestStock.getStockPrice(), minValue, maxValue, Math.round(average * 100D) / 100D);
            return stockDetails;
        }
        else{
            throw new StockNotFoundException("Stock details not found Exception for the company");
        }
    }

    public List<Stock> getAllStocks(String companyCode, String startDate, String endDate){
        Instant start = LocalDate.parse(startDate, dateTimeFormatter).atStartOfDay(ZoneId.of("+0")).toInstant();
        Instant end = LocalDate.parse(endDate, dateTimeFormatter).atStartOfDay(ZoneId.of("+0")).toInstant();
        return stockInfoRepository.retrieveStockDetails(companyCode, start, end);

    }
}
