package com.example.demo.model;

//import com.sun.istack.NotNull;
//import com.sun.istack.Nullable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Enfant extends Citoyen{
    private String nassmParent;
}
