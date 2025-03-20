package com.toctave.ma_recette.utilisateurs.services;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UtilisateurDto implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String preferencesAlimentaires;
    private String restrictionsDietetiques;
    private String objectif;
}
