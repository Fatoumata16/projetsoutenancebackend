package com.secuite.secur.repository;

import com.secuite.secur.modeles.formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repoformation extends JpaRepository<formation,Long> {
    formation findByNom(String nom);
}
