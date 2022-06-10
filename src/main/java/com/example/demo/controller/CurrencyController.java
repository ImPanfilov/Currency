package com.example.demo.controller;

import com.example.demo.services.GiphyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
public class CurrencyController {

    @Autowired
    private GiphyService giphyService;

    @GetMapping(path = "/compare/{symbols}")
    public ResponseEntity<?> getGiphy(@PathVariable("symbols") String symbols) throws IOException {

        return giphyService.getGiphy(symbols);
    }
}
