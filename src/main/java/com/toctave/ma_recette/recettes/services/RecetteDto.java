package com.toctave.ma_recette.recettes.services;

import com.toctave.ma_recette.ingredients.services.IngredientDto;
import lombok.Data;

import java.util.List;

@Data
public class RecetteDto {
    private Long id;
    private String titre;
    private String description;
    private String instructions;
    private int tempsPreparation;
    private int tempsCuisson;
    private String degreDifficultes;
    private String categorieRecette;
    private String imageUrl;
    private String valeursNutritionnelles;
    private List<IngredientDto> ingredients;
}
