package com.badyaxa.kraina.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class Kraina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @NotBlank
    private String url;

    private Long last;

    protected Kraina() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }
}
