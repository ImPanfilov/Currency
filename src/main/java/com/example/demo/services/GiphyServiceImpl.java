package com.example.demo.services;

import com.example.demo.client.CurrencyClient;
import com.example.demo.client.GifGetClient;
import com.example.demo.client.GiphyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiphyServiceImpl implements GiphyService{

    private final GiphyClient giphyClient;

    private final GifGetClient gifGetClient;

    private final CurrencyService currencyService;

    private final CurrencyClient currencyClient;

    @Override
    public ResponseEntity<?> getGiphy(String symbols) {
        String tag= currencyService.checkRate(symbols);
        return tag.equals("none")?currencyClient.currencies():
                          gifGetClient.downloadByUrl((String) giphyClient.getGif(tag).getData().get("id"));
    }

}
