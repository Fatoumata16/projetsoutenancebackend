package com.secuite.secur.modeles;


import lombok.Data;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Data

public class jury extends utilisateur{


    public jury(int id, String nom, String prenom, String username, String img, String mot2passe, role roles, Collection<promotion> promo,String email) {
        super(id, nom, prenom, username, img, mot2passe, roles, promo,email);
    }

    public jury() {
    }
}
