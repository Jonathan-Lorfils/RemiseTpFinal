package com.example.demo.repository;


import com.example.demo.model.Citoyen;
import com.example.demo.model.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Integer> {



}
