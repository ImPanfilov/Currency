package com.example.demo.services;

import org.springframework.http.ResponseEntity;

public interface GiphyService {
    ResponseEntity<?> getGiphy(String symbols);
}
