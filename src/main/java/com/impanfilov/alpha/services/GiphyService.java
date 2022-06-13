package com.impanfilov.alpha.services;

import com.impanfilov.alpha.client.CurrencyClient;
import com.impanfilov.alpha.client.GifDownloadClient;
import com.impanfilov.alpha.client.GiphyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiphyService{

    private final GiphyClient giphyClient;

    private final GifDownloadClient gifDownloadClient;

    private final CurrencyService currencyService;

    private final CurrencyClient currencyClient;

    public ResponseEntity<?> getGiphy(String currencyCode) {
        String tag = currencyService.checkRate(currencyCode);
        return tag.equals("none")?currencyClient.currencies():
                          gifDownloadClient.downloadByUrl((String) giphyClient.getGif(tag).getData().get("id"));
    }

}
