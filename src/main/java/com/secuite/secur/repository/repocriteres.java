package com.secuite.secur.repository;

import com.secuite.secur.modeles.criteresevaluation;
import com.secuite.secur.modeles.formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface repocriteres extends JpaRepository<criteresevaluation,Integer> {
    criteresevaluation findByIdo(int id);
    criteresevaluation findByNom(String nom);
    criteresevaluation findByNomAndFormations(String nom, formation p);
   List<criteresevaluation> findByFormations(formation p);

}
