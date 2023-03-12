package com.secuite.secur.modeles;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Collection;


@Data
@Entity
@AllArgsConstructor
public class admin extends utilisateur {
//    public admin(int id, String nom, String prenom, String username, String img, String mot2passe, role roles, Collection<promotion> promo) {
//        super(id, nom, prenom, username, img, mot2passe, roles, promo);
//    }

//    public admin() {
//    }

    public admin(int id, String nom, String prenom, String username, String img, String mot2passe, role roles, Collection<promotion> promo,String email) {
        super(id, nom, prenom, username, img, mot2passe, roles, promo,email);
    }
}
