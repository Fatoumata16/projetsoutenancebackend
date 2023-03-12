package com.secuite.secur.repository;

import com.secuite.secur.modeles.projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repoprojet extends JpaRepository<projet,Integer> {
//    projet findById(Long ide);
    projet findByNomn(String nom);
}
