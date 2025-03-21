package com.toctave.ma_recette.ingredients.services;

import com.toctave.ma_recette.ingredients.Ingredient;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDto toIngredientDto(Ingredient ingredient);
    Ingredient toIngredient(IngredientDto ingredientDto);
    List<IngredientDto> toIngredientDto(List<Ingredient> ingredients);
    List<Ingredient> toIngredient(List<IngredientDto> ingredientDtos);
}
