package com.secuite.secur.modeles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class promotion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int id;
    private String nom;
    private Date datedebut;
    private Date datefin;
    @ManyToOne(  cascade = CascadeType.MERGE )
    @JoinColumn(name = "idformation")
    private formation  formations;
}
