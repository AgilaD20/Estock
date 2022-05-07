package com.estock.estockmanager.ui;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Response {

    private String message;
    private Integer statusCode;
    private ZonedDateTime timestamp;
}
