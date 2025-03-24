package com.toctave.ma_recette.ingredients.services;

import lombok.Data;

@Data
public class IngredientDto {
    private Long id;
    private String nom;
    private String typeIngredient;
    private String valeursNutritionnelles;
}
