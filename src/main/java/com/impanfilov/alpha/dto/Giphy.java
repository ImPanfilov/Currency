package com.impanfilov.alpha.dto;

import lombok.Data;
import java.util.Map;

@Data
public class Giphy {
    Map<String, Object> data;
    Map<String, Object> meta;
}
