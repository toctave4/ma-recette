package com.toctave.ma_recette.compositions.services;

import lombok.Data;

@Data
public class CompositionDto {
    private Long recetteId;
    private Long ingredientId;
    private Double quantite;
    private String unite;
}
