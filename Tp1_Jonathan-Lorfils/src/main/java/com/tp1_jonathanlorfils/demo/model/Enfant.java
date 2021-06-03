package com.tp1_jonathanlorfils.demo.model;

import lombok.Data;
import javax.persistence.Entity;


@Data
@Entity
public class Enfant extends Citoyen{
    private String nassmParent;
}
