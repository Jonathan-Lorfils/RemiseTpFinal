package com.tp1_jonathanlorfils.demo.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Permis implements Serializable {

    @Id
    @GeneratedValue
    private Integer idPermis;

    private String typePermis;

    @Lob
    private byte[] codeQR;

    private LocalDate datePermisTest;

    private LocalDate ExpirationPermisTest;

    public Permis(){
        setDatePermisTest(LocalDate.now());
        setExpirationPermisTest(LocalDate.now().plusDays(14));
    }
}
