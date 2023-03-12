package com.secuite.secur.repository;

import com.secuite.secur.modeles.resultatvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface reporesult  extends JpaRepository<resultatvote,Long> {
}
