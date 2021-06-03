package com.tp1_jonathanlorfils.demo.repository;

import com.tp1_jonathanlorfils.demo.model.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CitoyenRepository extends JpaRepository<Citoyen, Integer> {

    Citoyen findCitoyenByCourrielAndMotDePasse(String courriel, String mdp);

    Citoyen findCitoyenByNumeroAssuranceSocial(String numeroAssuranceSocial);

    Citoyen findCitoyenById(Integer id);

    @Transactional
    void deleteCitoyenById(Integer id);

}
