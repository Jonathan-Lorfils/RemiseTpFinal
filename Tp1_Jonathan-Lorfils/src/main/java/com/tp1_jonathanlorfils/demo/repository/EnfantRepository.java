package com.tp1_jonathanlorfils.demo.repository;

import com.tp1_jonathanlorfils.demo.model.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Integer> {

}
