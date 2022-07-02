package com.badyaxa.kraina.controller;

import com.badyaxa.kraina.service.KrainaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import static com.badyaxa.kraina.KrainaApplication.VERSION_OF_THIS_APP;

@Slf4j
@RestController
public class HomePageController {

    @Autowired
    private KrainaService krainaService;

    @GetMapping
    public String home() {

        log.info("-------------HomePageController.home()>>>");

        return "file link is  " + krainaService.getFieldLast() + "" +
                "<br>" +
                "Local Time Is  <b>" + LocalTime.now() + "</b>" +
                "<br>" +
                "lastModified  - <b>" + krainaService.getFieldLast() + "</b>" +
                "<br>" +
                "<h4>VERSION " + VERSION_OF_THIS_APP + "</h4>" +
                "<br>" +
                "<a href=\"/actuator/health\" target=\"_blank\">actuator/health</a>" +
                "<br>" +
                "<a href=\"/swagger-ui.html\" target=\"_blank\">swagger-ui</a>" +
                "<br>" +
                "<br>" +
                "<a href=\"https://github.com/badyaxa/kraina-pdf\" target=\"_blank\">github repo</a>";
    }
}
