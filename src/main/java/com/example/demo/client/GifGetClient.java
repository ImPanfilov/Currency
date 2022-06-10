package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "download", url = "${download.url}",
        fallback = GifGetClientImlp.class)

public interface GifGetClient {

    @RequestMapping(method = RequestMethod.GET,value = "{idGif}.gif")
    ResponseEntity<byte[]> downloadByUrl(@PathVariable String idGif);

    }

