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
        final Long last = krainaRepository.findById(1L).get().getLast();
        log.info("---KrainaService.getLast(fromDatabase)>>>last = " + last);
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
        krainaRepository.save(gazeta);
        log.info("---KrainaService.updateLast(inDatabase)>>>last = " + last);
    }

    @Override
    public String getFieldtUrl() {
        final String url = krainaRepository.findById(1L).get().getUrl();
        log.info("---KrainaService.getdUrl(fromDatabase)>>>url = " + url);
        if (url == null) {
            return "";
        }
        return url;
    }

    @Override
    public Kraina create(Kraina kraina) {
        log.info("---KrainaService.create()>>>kraina = " + kraina);
        return krainaRepository.save(kraina);
    }
}
