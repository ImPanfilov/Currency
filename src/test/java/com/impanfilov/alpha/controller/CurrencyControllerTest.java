package com.impanfilov.alpha.controller;

import com.impanfilov.alpha.services.GiphyService;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import java.io.IOException;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8081)
public class CurrencyControllerTest {

    @Autowired
    GiphyService giphyService;

    @Test
    public void controllerRichOrBroke() throws Exception {

        stubFor(requestMatching(request ->
                MatchResult.of(request.getUrl().startsWith("/latest")))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/latest.json"))));

        stubFor(requestMatching(request ->
                MatchResult.of(request.getUrl().startsWith("/historical")))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/yesterday.json"))));

        stubFor(requestMatching(request ->
                MatchResult.of(request.getUrl().startsWith("/currencies")))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/currencies.json"))));

        stubFor(requestMatching(request ->
                MatchResult.of(request.getUrl().endsWith("rich.gif")))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.IMAGE_GIF_VALUE)
                        .withBody(read("stubs/rich.gif"))));

        stubFor(requestMatching(request ->
                MatchResult.of(request.getUrl().endsWith("broke.gif")))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.IMAGE_GIF_VALUE)
                        .withBody(read("stubs/broke.gif"))));

        stubFor(requestMatching(request ->
                MatchResult.of(request.getUrl().endsWith("rich")))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/rich.json"))));

        stubFor(requestMatching(request ->
                MatchResult.of(request.getUrl().endsWith("broke")))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/broke.json"))));


        byte[] rich = read("stubs/rich.gif");
        byte[] broke = read("stubs/broke.gif");


        //выборочные тесты на дату 2022-06-07
        assertArrayEquals(rich,(byte[])giphyService.getGiphy("RUB").getBody());//   latest "RUB": 61.749995        past "RUB": 61.374999
        assertArrayEquals(rich,(byte[])giphyService.getGiphy("CNY").getBody());//   latest "CNY": 6.6707           past "CNY": 6.6539
        assertArrayEquals(rich,(byte[])giphyService.getGiphy("BTC").getBody());//   latest "BTC": 0.000032070798   past "BTC": 0.000031889174

        assertArrayEquals(broke,(byte[])giphyService.getGiphy("EUR").getBody());//  latest "EUR": 0.934819         past "EUR": 0.935482
        assertArrayEquals(broke,(byte[])giphyService.getGiphy("CAD").getBody());//  latest "CAD": 1.253715         past "CAD": 1.258257
        assertArrayEquals(broke,(byte[])giphyService.getGiphy("GBP").getBody());//  latest "GBP": 0.794439         past "GBP": 0.798494
    }

    private byte[] read(String location) throws IOException {
        return IOUtils.toByteArray(new ClassPathResource(location).getInputStream());
    }
}
