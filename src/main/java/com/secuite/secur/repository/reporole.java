package com.secuite.secur.repository;

import com.secuite.secur.modeles.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface reporole extends JpaRepository<role ,Long> {
    role findByRolename(String nom);
}
