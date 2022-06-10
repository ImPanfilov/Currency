package com.example.demo.client;

import com.example.demo.dto.CurrrencyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CurrencyClientImpl implements CurrencyClient{

    @Override
    public CurrrencyDto currentRate() {
        return null;
    }

    @Override
    public CurrrencyDto pastRate(String string) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, String>> currencies() {
        return null;
    }


}
