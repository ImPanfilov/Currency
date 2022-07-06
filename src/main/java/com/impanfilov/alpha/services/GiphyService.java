package com.impanfilov.alpha.services;

import com.impanfilov.alpha.client.CurrencyClient;
import com.impanfilov.alpha.client.GifDownloadClient;
import com.impanfilov.alpha.client.GiphyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GiphyService{

    @Autowired
    GiphyClient giphyClient;

    @Autowired
    GifDownloadClient gifDownloadClient;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    CurrencyClient currencyClient;

    public ResponseEntity<?> getGiphy(String currencyCode) {
        String tag = currencyService.checkRate(currencyCode);
        return tag.equals("none")?currencyClient.currencies():
                          gifDownloadClient.downloadByUrl((String) giphyClient.getGif(tag).getData().get("id"));
    }

}
