package com.impanfilov.alpha.dto;

import lombok.Data;
import java.util.Map;

@Data
public class Currrency {
    String disclaimer;
    String license;
    long timestamp;
    String base;
    Map<String, Double> rates;
}
