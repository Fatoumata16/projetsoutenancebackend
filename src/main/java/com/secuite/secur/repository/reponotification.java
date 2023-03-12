package com.secuite.secur.repository;

import com.secuite.secur.modeles.notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface reponotification extends JpaRepository<notification,Long> {
    notification findByTitre(String nom);
   List<notification> findByExpeditaireAndDestinateurOrderByDateenvoieDesc(String expeditaire, String destinateur);
}
