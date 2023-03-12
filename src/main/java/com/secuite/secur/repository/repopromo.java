package com.secuite.secur.repository;

import com.secuite.secur.modeles.formation;
import com.secuite.secur.modeles.promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface repopromo extends JpaRepository<promotion,Integer> {
    promotion findByNom(String nom);
    promotion findById(int id);

   List<promotion> findByFormations(formation f);
}
