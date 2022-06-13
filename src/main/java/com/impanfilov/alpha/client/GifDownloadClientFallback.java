package com.impanfilov.alpha.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GifDownloadClientFallback implements GifDownloadClient {

    @Override
    public ResponseEntity<byte[]> downloadByUrl(String string){
        return null;
    }
}
