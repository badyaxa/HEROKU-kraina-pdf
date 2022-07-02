package com.badyaxa.kraina.service;

import com.badyaxa.kraina.entity.Kraina;
import com.badyaxa.kraina.repository.KrainaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class KrainaServiceImpl implements KrainaService {

    @Autowired
    private KrainaRepository krainaRepository;

    @Override
    public Kraina getKrainaById(Long id) {
        return krainaRepository.getKrainaById(id);
    }

    @Override
    public Long getFieldLast() {
        log.info("------------------GazetaServiceImpl.getFieldLast()");
        final Long last = krainaRepository.findById(1L).get().getLast();
        log.info("-------------GazetaServiceImpl.getFieldLast() ---   last = " + last);
        if (last == null) {
            return -1L;
        }
        return last;
    }

    @Override
    public void updateFieldLast(Long last) {
        final Optional<Kraina> gazetaOptional = krainaRepository.findById(1L);
        final Kraina gazeta = gazetaOptional.get();
        gazeta.setLast(last);
        System.out.println("updateFieldLast()");
        /*final Gazeta save = */
        krainaRepository.save(gazeta);
//        log.info("-------------GazetaServiceImpl.updateFieldLast() " + save.getLast());
    }

    @Override
    public String getFieldtUrl() {
        log.info("------------------GazetaServiceImpl.getFieldUrl");
        final String url = krainaRepository.findById(1L).get().getUrl();
        if (url == null) {
            return "";
        }
        return url;
    }

    @Override
    public Kraina create(Kraina kraina) {
        return krainaRepository.save(kraina);
    }
}
