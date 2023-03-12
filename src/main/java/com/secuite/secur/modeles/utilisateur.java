package com.secuite.secur.modeles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class utilisateur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private  int ide;
    private String nom;
    private String prenom;
    private  String username;
    private String img;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String mot2passe;
    @ManyToOne( )
    @JoinColumn(name = "idrole")
    private role roles ;
    @ManyToMany ( cascade = CascadeType.MERGE )
    private Collection<promotion> promo ;
    @ManyToMany ()
    private Collection<notification> notifications ;
    private String email;

    public utilisateur(String nom, String prenom, String username, String mot2passe, role roles, Collection<promotion> promo,String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;

        this.mot2passe = mot2passe;
        this.roles = roles;
        this.promo = promo;
        this.email=email;
    }


    public utilisateur(int id, String nom,String prenom,String username,String img, String mot2passe, role roles,Collection<promotion>promo,String email) {
        this.ide = id;
        this.nom = nom;
        this.prenom=prenom
                ;
        this.username=username;

        this.img=img;
        this.mot2passe = mot2passe;
        this.roles = roles;
        this.promo=promo;
        this.email=email;

    }



//    public utilisateur(Long id, String nom, String mot2passe, role roles) {
//        this.id = id;
//        this.nom = nom;
//        this.mot2passe = mot2passe;
//        this.roles = roles;
//    }

}
