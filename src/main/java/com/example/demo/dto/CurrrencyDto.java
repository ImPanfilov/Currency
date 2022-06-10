package com.example.demo.dto;

import lombok.Data;
import java.util.Map;

@Data
public class CurrrencyDto {
    String disclaimer;
    String license;
    long timestamp;
    String base;
    Map<String, Double> rates;
}
