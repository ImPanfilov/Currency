package com.impanfilov.alpha.services;

import com.impanfilov.alpha.client.CurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyService{

    @Autowired
    CurrencyClient currencyClient;

    public String checkRate(String currencyCode){
        Double currRate = currencyClient.currentRate().getRates().get(currencyCode);
        Double pastRate = currencyClient.pastRate(LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE)).getRates().get(currencyCode);
        return pastRate == null?"none":(pastRate <= currRate?"rich":"broke");
    }

}

