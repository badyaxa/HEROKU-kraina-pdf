package com.badyaxa.kraina.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

@Slf4j
@Component
@AllArgsConstructor
public class ScheduledService {

    @Autowired
    private KrainaService krainaService;

    @Scheduled(fixedDelayString = "${scheduler.fixed.delay.milis}")
    public void checkHeaderFieldLastModified() {

        log.info("---ScheduledService.checkHeaderFieldLastModified()---------");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user-agent", "PostmanRuntime/7.29.0");
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        //service return only URL with 1 ID
        String fieldtUrl = krainaService.getFieldtUrl();

        ResponseEntity<byte[]> responseEntity =
                restTemplate.exchange(fieldtUrl, HttpMethod.GET, httpEntity, byte[].class);

        final long lastModified = responseEntity.getHeaders().getLastModified();
        LocalDate date = Instant.ofEpochMilli(lastModified)
                .atZone(ZoneId.of("UTC+3"))
                .toLocalDate();



        final String string = Instant.ofEpochMilli(lastModified)
                .atZone(ZoneId.of("UTC+3")).toLocalDateTime().toString();
        log.info("lastModified.toLocalDateTime = " + string);




        final long fieldLast = krainaService.getFieldLast();
        if (lastModified != fieldLast) {
            krainaService.updateFieldLast(lastModified);
            try {
                Files.write(Paths.get("" + date + ".pdf"), responseEntity.getBody());
            } catch (IOException e) {
                throw new RuntimeException("(File system problem: error trying to write file to folder) " + e);
            }
        }
    }
}
