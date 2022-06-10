package com.example.demo.client;

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
import java.nio.charset.StandardCharsets;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8081)
public class GiphyClientTest {

    @Autowired
    GiphyClient giphyClient;

    @Test
    public void getRichOrBroke() throws Exception {


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

        assertEquals("rich",giphyClient.getGif("rich").getData().get("id"));
        assertEquals("broke",giphyClient.getGif("broke").getData().get("id"));

    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}
