package com.badyaxa.kraina;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import static com.badyaxa.kraina.KrainaApplication.VERSION_OF_THIS_APP;

@Slf4j
@RestController
public class HomePageController {

    @GetMapping
    public String home() {

        log.info("-------------HomePageController.home()>>>");

        return "file link is  " + "fieldtUrl" + "" +
                "<br>" +
                "Local Time Is  <b>" + LocalTime.now() + "</b>" +
                "<br>" +
                "lastModified  - <b>" + "lastString" + "</b>" +
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
