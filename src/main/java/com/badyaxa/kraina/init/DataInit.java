package com.badyaxa.kraina.init;

import com.badyaxa.kraina.entity.Kraina;
import com.badyaxa.kraina.repository.KrainaRepository;
import com.badyaxa.kraina.service.KrainaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DataInit implements ApplicationRunner {

    @Autowired
    private KrainaService krainaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       final Kraina kraina = new Kraina("https://gazeta.ua/static/pdf/journal.pdf");
        krainaService.create(kraina);
        log.info("--------------DataInit.run()--------------" + "saved");
    }
}
