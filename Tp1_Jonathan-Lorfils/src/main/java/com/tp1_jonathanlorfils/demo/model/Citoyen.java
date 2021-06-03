package com.tp1_jonathanlorfils.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Citoyen extends User{

    @Column(unique = true)
    private String numeroAssuranceSocial;

    private String nom;

    private String prenom;

    private int age;

    private String typePermis;

    @OneToOne
    private Permis permis;
}
