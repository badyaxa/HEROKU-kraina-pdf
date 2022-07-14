package com.badyaxa.kraina.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
@Component
@AllArgsConstructor
public class ScheduledService {

    @Autowired
    private KrainaService krainaService;

    @Scheduled(fixedDelayString = "${scheduler.fixed.delay.milis}")
    public void checkHeaderFieldLastModified() {

        log.info("---ScheduledService.begin>>>");
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("user-agent", "PostmanRuntime/7.29.0");
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        //service return only URL with ID=1L
        String fieldUrl = krainaService.getFieldtUrl();

        ResponseEntity<byte[]> responseEntity =
                restTemplate.exchange(fieldUrl, HttpMethod.GET, httpEntity, byte[].class);

        final long lastModified = responseEntity.getHeaders().getLastModified();
        LocalDate date = Instant.ofEpochMilli(lastModified)
                .atZone(ZoneId.of("UTC+3"))
                .toLocalDate();

        final String localDateTimeUtc3 = Instant.ofEpochMilli(lastModified)
                .atZone(ZoneId.of("UTC+3"))
                .toLocalDateTime().toString();
        log.info("---ScheduledService.lastModified.FromSite = " + localDateTimeUtc3);

        final long fieldLast = krainaService.getFieldLast();
        final String fieldLastUtc3 = Instant.ofEpochMilli(lastModified)
                .atZone(ZoneId.of("UTC+3"))
                .toLocalDateTime().toString();
        log.info("---ScheduledService.lastModified.FromDatabase = " + fieldLastUtc3);

        if (lastModified != fieldLast) {

            log.info("---ScheduledService.if()>>>New file on server found");

            krainaService.updateFieldLast(lastModified);

            try {
                log.info("---ScheduledService.try>>>");
                Files.write(Paths.get("" + date + ".pdf"), responseEntity.getBody());

                log.info("---ScheduledService.try>>>file downloaded, now send notification to telegram");
                URL url = null;
                HttpURLConnection con = null;
                try {
                    log.info("---ScheduledService.try.try>>>");
                    url = new URL(System.getenv("TELEGRAM_BOT_URL") + localDateTimeUtc3);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.getResponseCode();
                } catch (MalformedURLException e) {
                    log.info("---ScheduledService.try.catch1 MalformedURLException>>>");
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    log.info("---ScheduledService.try.catch2 IOException>>>");
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                log.info("---ScheduledService.catch IOException>>>");
                throw new RuntimeException("(File system problem: error trying to write file to folder) " + e);
            }
        } else {
            log.info("---ScheduledService.else()>>>No new file on server");
        }
    }
}
