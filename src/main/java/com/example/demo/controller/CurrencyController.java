package com.example.demo.controller;

import com.example.demo.services.GiphyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final GiphyService giphyService;

    @GetMapping(path = "/compare/{symbols}")
    public ResponseEntity<?> getGiphy(@PathVariable("symbols") String symbols) {

        return giphyService.getGiphy(symbols);
    }
}
