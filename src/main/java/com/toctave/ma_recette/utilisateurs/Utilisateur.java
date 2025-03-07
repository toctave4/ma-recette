package com.toctave.ma_recette.utilisateurs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String preferencesAlimentaires;
    private String restrictionsDietetiques;
    private String objectif;
}
