package com.example.demo.repository;

import com.example.demo.model.Citoyen;
import com.example.demo.model.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CitoyenRepository extends JpaRepository<Citoyen, Integer> {

    public Citoyen findCitoyenByCourriel(String courriel);

    public Citoyen findCitoyenByCourrielAndMotDePasse(String courriel, String mdp);

    public Citoyen findCitoyenByNumeroAssuranceSocial(String numeroAssuranceSocial);

    Citoyen findCitoyenByPermis(Permis permis);

    Citoyen findCitoyenById(Integer id);

    @Transactional
    public void deleteCitoyenById(Integer id);
}
