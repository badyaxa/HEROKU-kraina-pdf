package com.badyaxa.kraina.service;

import org.springframework.stereotype.Service;

import com.badyaxa.kraina.entity.Kraina;

@Service
public interface KrainaService {

    Kraina getKrainaById(Long id);
    Long getFieldLast();
    void updateFieldLast(Long last);
    String getFieldtUrl();
    void create(Kraina kraina);
}
