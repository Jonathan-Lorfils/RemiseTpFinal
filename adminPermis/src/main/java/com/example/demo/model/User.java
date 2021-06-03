package com.example.demo.model;

//import com.sun.istack.NotNull;
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

//    @NotNull
    private String motDePasse;
}
