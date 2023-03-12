package com.secuite.secur.modeles;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
public class Etudiant extends utilisateur{

    private boolean lu;
@ManyToOne( )
@JoinColumn(name = "idsoutenance")
private soutenance soutenances ;

//    public Etudiant(int ide, String nom, String prenom, String username, String img, String mot2passe, role roles, Collection<promotion> promo, Collection<notification> notifications,boolean lu, soutenance soutenances) {
//        super(ide, nom, prenom, username, img, mot2passe, roles, promo, notifications.toString());
//        this.lu=lu;
//        this.soutenances = soutenances;
//    }









    public Etudiant(String nom, String prenom, String username, String mot2passe, role roles, Collection<promotion> promo,String email, soutenance soutenances) {
        super(nom, prenom, username, mot2passe, roles, promo,email);
        this.soutenances = soutenances;
    }

    public Etudiant(int id, String nom, String prenom, String username, String img, String mot2passe, role roles, Collection<promotion> promo,String email, soutenance soutenances) {
        super(id, nom, prenom, username, img, mot2passe, roles, promo,email);
        this.soutenances = soutenances;
    }

    @Override
    public int getIde() {
        return super.getIde();
    }

    @Override
    public String getPrenom() {
        return super.getPrenom();
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getImg() {
        return super.getImg();
    }

    @Override
    public String getMot2passe() {
        return super.getMot2passe();
    }

    @Override
    public role getRoles() {
        return super.getRoles();
    }


    public Collection<promotion> getPromo() {
        return super.getPromo();
    }


    @Override
    public void setIde(int ide) {
        super.setIde(ide);
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom);
    }

    @Override
    public void setPrenom(String prenom) {
        super.setPrenom(prenom);
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public void setImg(String img) {
        super.setImg(img);
    }

    @Override
    public void setRoles(role roles) {
        super.setRoles(roles);
    }

    @Override
    public void setMot2passe(String mot2passe) {
        super.setMot2passe(mot2passe);
    }


    public void setPromo(Collection<promotion> promo) {
        super.setPromo(promo);
    }


    public Etudiant(String nom, String prenom, String username, String mot2passe, role roles, Collection<promotion> promo,String email) {
        super(nom, prenom, username, mot2passe, roles, promo,email);
    }

    public Etudiant(int id, String nom, String prenom, String username, String img, String mot2passe, role roles, Collection<promotion> promo,String email) {
        super(id, nom, prenom, username, img, mot2passe, roles, promo,email);
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public void setSoutenances(soutenance soutenances) {
        this.soutenances = soutenances;
    }

    public boolean isLu() {
        return lu;
    }

    public soutenance getSoutenances() {
        return soutenances;
    }

    public Etudiant() {
    }

//    public Etudiant(String nom, String prenom, String username, String mot2passe, role roles, Collection<promotion> promo) {
//        super(nom, prenom, username, mot2passe, roles, promo);
//    }
//
//    public Etudiant(int id, String nom, String prenom, String username, String img, String mot2passe, role roles, Collection<promotion> promo) {
//        super(id, nom, prenom, username, img, mot2passe, roles, promo);
//    }
}
