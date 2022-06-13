package com.impanfilov.alpha.controller;

import com.impanfilov.alpha.services.GiphyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final GiphyService giphyService;

    @GetMapping(path = "/compare/{currencyCode}")
    public ResponseEntity<?> getGiphy(@PathVariable("currencyCode") String symbols) {

        return giphyService.getGiphy(symbols);
    }
}
