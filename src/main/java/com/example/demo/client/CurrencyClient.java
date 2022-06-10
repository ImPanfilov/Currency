package com.example.demo.client;

import com.example.demo.dto.CurrrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;

@FeignClient(name = "currency", url = "${currency.url}")
public interface CurrencyClient {

    @RequestMapping(method = RequestMethod.GET,value = "/latest.json?app_id=${currency.app_id}&base=${currency.base}")
    CurrrencyDto currentRate();

    @RequestMapping(method = RequestMethod.GET,value = "/historical/{past_date}.json?app_id=${currency.app_id}&base=${currency.base}")
    CurrrencyDto pastRate(@PathVariable("past_date") String pastDate);

    @RequestMapping(method = RequestMethod.GET,value = "/currencies.json")
    ResponseEntity<Map<String, String>> currencies();
}


