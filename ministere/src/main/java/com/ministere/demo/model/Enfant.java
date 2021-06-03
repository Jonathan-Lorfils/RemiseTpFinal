package com.ministere.demo.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;


@Entity
@Data
public class Enfant extends Citoyen implements Serializable {
    private String nassmTuteur;
}
