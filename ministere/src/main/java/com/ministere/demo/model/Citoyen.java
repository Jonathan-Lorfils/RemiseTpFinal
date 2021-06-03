package com.ministere.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Citoyen implements Serializable{
    @Id
    private Integer id;
    private String nomComplet;
    private String nassm;
    private String type;
}
