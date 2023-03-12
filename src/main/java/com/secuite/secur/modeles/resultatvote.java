package com.secuite.secur.modeles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class resultatvote {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    private String nom;
    private String prenom;
    private  String autrenom;
    private  Double resultat;
}
