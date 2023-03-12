package com.secuite.secur.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class soutenance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;
    private String nom;
    private  String img;
    private Date datedebut;
    private LocalTime heuredeb;
    private LocalTime heurefin;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<jury> jurys = new ArrayList<>();
    @ManyToOne(  cascade = CascadeType.MERGE )
    @JoinColumn(name = "idadmin")
    private admin  admi;
    private boolean type;
    private boolean resultat;

    public boolean isType() {
        return type;
    }
}
