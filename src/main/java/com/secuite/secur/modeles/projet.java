package com.secuite.secur.modeles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class projet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    String nomn;
    String description;
    @OneToOne( cascade = CascadeType.MERGE)
    private Etudiant etudiant;
}
