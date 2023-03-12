package com.secuite.secur.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class criteresevaluation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int ido;
    private  String nom;
    private int coefficient;
    @ManyToOne( )
    @JoinColumn(name = "idformation")
    private formation formations;
}
