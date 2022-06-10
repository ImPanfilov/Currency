package com.example.demo.dto;

import lombok.Data;
import java.util.Map;

@Data
public class GiphyDto {
    Map<String, Object> data;
    Map<String, Object> meta;
}
