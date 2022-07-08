package com.badyaxa.kraina.service;

import com.badyaxa.kraina.entity.Kraina;

public interface KrainaService {

    Kraina getKrainaById(Long id);

    Long getFieldLast();

    void updateFieldLast(Long last);

    String getFieldtUrl();

    Kraina create(Kraina kraina);
}
