package com.impanfilov.alpha.client;

import com.impanfilov.alpha.dto.Currrency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CurrencyClientFallback implements CurrencyClient{

    @Override
    public Currrency currentRate() {
        return null;
    }

    @Override
    public Currrency pastRate(String string) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, String>> currencies() {
        return null;
    }


}
