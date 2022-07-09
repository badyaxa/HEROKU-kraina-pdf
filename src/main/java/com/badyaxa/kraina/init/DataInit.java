package com.badyaxa.kraina.init;

import com.badyaxa.kraina.entity.Kraina;
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

        String link = "https://gazeta.ua/static/pdf/journal.pdf";
        final Kraina krainaById = krainaService.getKrainaById(1L);
        if (krainaById.getUrl().equals(link)){
            log.info("---DataInit.run()>>>link already is in Database = " + link);
        } else{
            final Kraina kraina = new Kraina(1L, "https://gazeta.ua/static/pdf/journal.pdf", -1L);
            final Kraina saved = krainaService.create(kraina);
            log.info("---DataInit.run()>>>Database in empty, add =" + saved);
        }
    }
}
