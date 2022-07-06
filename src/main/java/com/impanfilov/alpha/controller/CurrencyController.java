package com.impanfilov.alpha.controller;

import com.impanfilov.alpha.services.GiphyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyController {

    @Autowired
    GiphyService giphyService;

    @GetMapping(path = "/compare/{currencyCode}")
    public ResponseEntity<?> getGiphy(@PathVariable("currencyCode") String symbols) {

        return giphyService.getGiphy(symbols);
    }
}
