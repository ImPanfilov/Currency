package com.example.demo.services;

import com.example.demo.client.CurrencyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyClient currencyClient;

    @Override
    public String checkRate(String symbols){
        Double currRate = currencyClient.currentRate().getRates().get(symbols);
        Double pastRate = currencyClient.pastRate(LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE)).getRates().get(symbols);
        return pastRate==null?"none":(pastRate<= currRate?"rich":"broke");
    }

}

