package com.tp1_jonathanlorfils.demo.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String courriel;

    private String motDePasse;
}
