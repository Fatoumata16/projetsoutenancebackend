package com.secuite.secur.repository;

import com.secuite.secur.modeles.jury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repojury extends JpaRepository<jury,Integer> {
   jury findByIde(int id);
    jury findByNom(String nom);
    jury findByUsername(String n);
}
