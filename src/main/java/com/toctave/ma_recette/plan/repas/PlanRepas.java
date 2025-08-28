package com.toctave.ma_recette.plan.repas;

import com.toctave.ma_recette.recettes.Recette;
import com.toctave.ma_recette.utilisateurs.Utilisateur;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class PlanRepas {
    @Id
    private Long id;
    private String nom;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Utilisateur utilisateur;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Recette> recettes;
    private Date date;
    private String objectif;
}
