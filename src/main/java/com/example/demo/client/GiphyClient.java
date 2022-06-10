package com.example.demo.client;

import com.example.demo.dto.GiphyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "giphy", url = "${giphy.url}",
        fallback = GiphyClientImpl.class)
public interface GiphyClient {

    @RequestMapping(method = RequestMethod.GET,value = "/random?api_key=${giphy.api_key}&rating=${giphy.rating}")
    GiphyDto getGif(@RequestParam("tag") String tag);
}
