package com.estock.estockmanager.controller;

import com.estock.estockmanager.Entity.Stock;
import com.estock.estockmanager.dto.StockDetails;
import com.estock.estockmanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/market/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/add/{companycode}")
    public ResponseEntity<Stock> addStock(@RequestParam Double stock, @PathVariable("companycode") String companyCode){
        Stock addedStock = stockService.addstock(stock,companyCode);
        return new ResponseEntity<>(addedStock, HttpStatus.OK);
    }

    @GetMapping("get/{companycode}/{startdate}/{enddate}")
    public ResponseEntity<StockDetails> getStockDetails(@PathVariable("companycode") String companyCode, @PathVariable("startdate") String startDate, @PathVariable("enddate") String endDate){
        StockDetails stockDetails = stockService.getStockDetails(companyCode,startDate,endDate);
        return new ResponseEntity<>(stockDetails,HttpStatus.OK);

    }

    @GetMapping("info/{companycode}/{startdate}/{enddate}")
    public ResponseEntity<List<Stock>> getAllStocks(@PathVariable("companycode") String companyCode, @PathVariable("startdate") String startDate, @PathVariable("enddate") String endDate){
        List<Stock> stocks = stockService.getAllStocks(companyCode,startDate,endDate);
        return new ResponseEntity<>(stocks,HttpStatus.OK);

    }



}
