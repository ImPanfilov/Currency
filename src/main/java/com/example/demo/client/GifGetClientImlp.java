package com.example.demo.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GifGetClientImlp implements GifGetClient{

    @Override
    public ResponseEntity<byte[]> downloadByUrl(String string){
        return null;
    }
}
