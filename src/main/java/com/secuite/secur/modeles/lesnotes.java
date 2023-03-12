package com.secuite.secur.modeles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class lesnotes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    private int note;
    private boolean valider;
    private  String commentaires;
    private Date date;
    @ManyToOne( )
    @JoinColumn(name = "idetudiant")
    private  Etudiant etudiant ;
    @ManyToOne( )
    @JoinColumn(name = "idjury")
    private jury jurys ;

    @ManyToOne()
    @JoinColumn(name = "idcriteres")
    private criteresevaluation criteres ;


    public boolean getValider() {
        return valider;
    }
}
