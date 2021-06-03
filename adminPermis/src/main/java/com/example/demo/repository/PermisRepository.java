package com.example.demo.repository;

import com.example.demo.model.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PermisRepository extends JpaRepository<Permis, Integer> {

    public List<Permis> findPermisByTypePermis(String typePermis);

    public Permis findPermisByIdPermis(int id);

    @Transactional
     void deleteByIdPermis(Integer id);
}