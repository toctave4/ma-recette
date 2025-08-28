package com.toctave.ma_recette.ingredients.services;

import com.toctave.ma_recette.ingredients.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientMapperTest {

    private final IngredientMapper ingredientMapper = IngredientMapper.INSTANCE;

    @Test
    public void testToIngredientDto() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setNom("Tomato");

        IngredientDto ingredientDto = ingredientMapper.toIngredientDto(ingredient);

        assertEquals(1L, ingredientDto.getId());
        assertEquals("Tomato", ingredientDto.getNom());
    }

    @Test
    public void testToIngredient() {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(1L);
        ingredientDto.setNom("Tomato");

        Ingredient ingredient = ingredientMapper.toIngredient(ingredientDto);

        assertEquals(1L, ingredient.getId());
        assertEquals("Tomato", ingredient.getNom());
    }

    @Test
    public void testToIngredientDtoList() {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setNom("Tomato");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setNom("Cucumber");

        List<Ingredient> ingredients = Arrays.asList(ingredient1, ingredient2);
        List<IngredientDto> ingredientDtos = ingredientMapper.toIngredientDto(ingredients);

        assertEquals(2, ingredientDtos.size());
        assertEquals(1L, ingredientDtos.get(0).getId());
        assertEquals("Tomato", ingredientDtos.get(0).getNom());
        assertEquals(2L, ingredientDtos.get(1).getId());
        assertEquals("Cucumber", ingredientDtos.get(1).getNom());
    }
}
