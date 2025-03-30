package com.toctave.ma_recette.recettes;

import com.toctave.ma_recette.ingredients.Ingredient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Recette {
    @Id
    private Long id;
    private String titre;
    private String description;
    private String instructions;
    private int tempsPreparation;
    private int tempsCuisson;
    private DegreDifficultes degreDifficultes;
    private CategorieRecette categorieRecette;
    private String imageUrl;
    private String valeursNutritionnelles;
}
