package com.example.demo.services;

import com.example.demo.client.CurrencyClient;
import com.example.demo.client.GifGetClient;
import com.example.demo.client.GiphyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class GiphyService {

    @Autowired
    private GiphyClient giphyClient;

    @Autowired
    private GifGetClient gifGetClient;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyClient currencyClient;

    public ResponseEntity<?> getGiphy(String symbols) {
        String tag=currencyService.checkRate(symbols);
        return tag.equals("none")?currencyClient.currencies():
                          gifGetClient.downloadByUrl((String) giphyClient.getGif(tag).getData().get("id"));
    }

}
