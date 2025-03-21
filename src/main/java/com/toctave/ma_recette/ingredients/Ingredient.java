package com.toctave.ma_recette.ingredients;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingredient {
    @Id
    private Long id;
    private String nom;
    private TypeIngredient typeIngredient;
    private String valeursNutritionnelles;
}
