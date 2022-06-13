package com.impanfilov.alpha.client;

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
import java.io.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8081)
public class GifDownloadClientTest {

    @Autowired
    GifDownloadClient gifDownloadClient;

    @Test
    public void getGif_RichOrBroke() throws Exception {

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


        byte[] rich = read("stubs/rich.gif");
        byte[] broke = read("stubs/broke.gif");

        assertArrayEquals(rich, gifDownloadClient.downloadByUrl("rich").getBody());
        assertArrayEquals(broke, gifDownloadClient.downloadByUrl("broke").getBody());

    }

    private byte[] read(String location) throws IOException {
        return IOUtils.toByteArray(new ClassPathResource(location).getInputStream());
    }
}
