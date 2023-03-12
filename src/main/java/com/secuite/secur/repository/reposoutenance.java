package com.secuite.secur.repository;

import com.secuite.secur.modeles.jury;
import com.secuite.secur.modeles.soutenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface reposoutenance extends JpaRepository<soutenance,Integer> {
    soutenance findByNom(String nom);

//    soutenance findById(int nom);

//    soutenance findByJurysAndDatedebutBetweenDatefinAndHeuredebBetweenHeurefin(
//            jury j,
//            Date startDate,
//            Date endDate,
//            LocalTime startTime,
//            LocalTime endTime
//    );

 List<soutenance>  findByJurysAndDatedebutLessThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(
            jury j, Date startDate,  LocalTime startTime, LocalTime endTime);
}

