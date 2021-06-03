package com.ministere.demo.repository;

import com.ministere.demo.model.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Integer> {

    Enfant findEnfantByNassm(String nassm);

    @Transactional
    void deleteEnfantsById(Integer id);
}
