package com.secuite.secur.repository;

import com.secuite.secur.modeles.Etudiant;
import com.secuite.secur.modeles.criteresevaluation;
import com.secuite.secur.modeles.jury;
import com.secuite.secur.modeles.lesnotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface reponote extends JpaRepository<lesnotes,Long> {
    lesnotes findByEtudiantAndJurysAndCriteres(Etudiant etudiant, jury jury, criteresevaluation c);
  List<lesnotes> findByEtudiant(Etudiant etudiant);
  List<lesnotes> findByEtudiantAndCriteres(Etudiant e,criteresevaluation j);
}
