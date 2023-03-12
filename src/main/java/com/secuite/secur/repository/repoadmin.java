package com.secuite.secur.repository;

import com.secuite.secur.modeles.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repoadmin extends JpaRepository<admin,Integer> {
}
