package com.estock.estockmanager.controller;

import com.estock.estockmanager.exception.StockNotFoundException;
import com.estock.estockmanager.ui.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class StockControllerAdvice {

    @ExceptionHandler({StockNotFoundException.class})
    public ResponseEntity<Response> stockNotFoundException(){
        Response response = new Response("Stock not found exception", HttpStatus.NOT_FOUND.value(), ZonedDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
