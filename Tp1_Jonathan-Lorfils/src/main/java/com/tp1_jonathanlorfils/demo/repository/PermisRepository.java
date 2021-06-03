package com.tp1_jonathanlorfils.demo.repository;

import com.tp1_jonathanlorfils.demo.model.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisRepository extends JpaRepository<Permis, Integer> {

}