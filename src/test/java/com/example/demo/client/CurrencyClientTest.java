package com.example.demo.client;

import com.example.demo.dto.CurrrencyDto;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8081)
public class CurrencyClientTest {

    @Autowired
    CurrencyClient currencyClient;

    @Test
    public void getCurrenciesRate_returnValidResponse() throws Exception {

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


        ResponseEntity<Map<String, String>> currencies = currencyClient.currencies();
        CurrrencyDto currrencyPast = currencyClient.pastRate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        CurrrencyDto currrencyLast = currencyClient.currentRate();


        //выборочные тесты на дату 2022-06-07
        assertEquals("Russian Ruble",((String)currencies.getBody().get("RUB")));
        assertEquals(61.749995,currrencyLast.getRates().get("RUB"));
        assertEquals(61.374999,currrencyPast.getRates().get("RUB"));

        assertEquals("Bitcoin",((String)currencies.getBody().get("BTC")));
        assertEquals(0.000032070798,currrencyLast.getRates().get("BTC"));
        assertEquals(0.000031889174,currrencyPast.getRates().get("BTC"));
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }

}
