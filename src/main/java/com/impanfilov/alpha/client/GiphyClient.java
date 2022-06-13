package com.impanfilov.alpha.client;

import com.impanfilov.alpha.dto.Giphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "giphy", url = "${giphy.url}",
        fallback = GiphyClientFallback.class)

public interface GiphyClient {

    @RequestMapping(method = RequestMethod.GET,value = "/random?api_key=${giphy.api_key}&rating=${giphy.rating}")
    Giphy getGif(@RequestParam("tag") String tag);
}
