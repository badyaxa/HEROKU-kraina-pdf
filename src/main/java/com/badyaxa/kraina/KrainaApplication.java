package com.badyaxa.kraina;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

@Slf4j
@SpringBootApplication
public class KrainaApplication {

    public static final String VERSION_OF_THIS_APP = "0.4";

    public static void main(String[] args) {
        SpringApplication.run(KrainaApplication.class, args);

        log.info("-------------------------------environment>>>");
        Map<String, String> environment = System.getenv();
        environment.forEach((k, v) -> System.out.println("---" + k + ":" + v));
        log.info("<<<environment----------------------------beans>>>");
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        for (String name : context.getBeanDefinitionNames()) {
            log.info("---BeanName = " + name);
        }
        log.info("<<<beans-------------------------------");
    }
}
