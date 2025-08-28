package com.toctave.ma_recette.compositions.services;

import com.toctave.ma_recette.compositions.Composition;
import com.toctave.ma_recette.compositions.CompositionId;
import com.toctave.ma_recette.recettes.Recette;
import com.toctave.ma_recette.ingredients.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompositionMapperTest {

    private final CompositionMapper compositionMapper = CompositionMapper.INSTANCE;

    @Test
    public void testToCompositionDto() {
        Recette recette = new Recette();
        recette.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        CompositionId compositionId = new CompositionId();
        compositionId.setRecette(recette);
        compositionId.setIngredient(ingredient);
        Composition composition = new Composition();
        composition.setId(compositionId);

        CompositionDto compositionDto = compositionMapper.toCompositionDto(composition);

        assertEquals(1L, compositionDto.getRecetteId());
        assertEquals(2L, compositionDto.getIngredientId());
    }

    @Test
    public void testToComposition() {
        CompositionDto compositionDto = new CompositionDto();
        compositionDto.setRecetteId(1L);
        compositionDto.setIngredientId(2L);

        Composition composition = compositionMapper.toComposition(compositionDto);

        assertEquals(1L, composition.getId().getRecette().getId());
        assertEquals(2L, composition.getId().getIngredient().getId());
    }

    @Test
    public void testToCompositionDtoList() {
        Recette recette = new Recette();
        recette.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        CompositionId compositionId = new CompositionId();
        compositionId.setRecette(recette);
        compositionId.setIngredient(ingredient);
        Composition composition = new Composition();
        composition.setId(compositionId);

        List<Composition> compositions = Arrays.asList(composition);
        List<CompositionDto> compositionDtos = compositionMapper.toCompositionDto(compositions);

        assertEquals(1, compositionDtos.size());
        assertEquals(1L, compositionDtos.get(0).getRecetteId());
        assertEquals(2L, compositionDtos.get(0).getIngredientId());
    }

    @Test
    public void testToCompositionList() {
        CompositionDto compositionDto = new CompositionDto();
        compositionDto.setRecetteId(1L);
        compositionDto.setIngredientId(2L);

        List<CompositionDto> compositionDtos = Arrays.asList(compositionDto);
        List<Composition> compositions = compositionMapper.toComposition(compositionDtos);

        assertEquals(1, compositions.size());
        assertEquals(1L, compositions.get(0).getId().getRecette().getId());
        assertEquals(2L, compositions.get(0).getId().getIngredient().getId());
    }
}
