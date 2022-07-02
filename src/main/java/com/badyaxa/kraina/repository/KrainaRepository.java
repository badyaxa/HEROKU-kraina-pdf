package com.badyaxa.kraina.repository;

import com.badyaxa.kraina.entity.Kraina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KrainaRepository extends JpaRepository<Kraina, Long> {
    Kraina getKrainaById(Long id);
}
