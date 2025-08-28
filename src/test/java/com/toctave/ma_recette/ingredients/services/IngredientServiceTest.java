package com.toctave.ma_recette.ingredients.services;

import com.toctave.ma_recette.ingredients.Ingredient;
import com.toctave.ma_recette.ingredients.repositories.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Ingredient> ingredients = Arrays.asList(new Ingredient(), new Ingredient());
        when(ingredientRepository.findAll()).thenReturn(ingredients);

        List<IngredientDto> result = ingredientService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));

        IngredientDto result = ingredientService.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindById_NotFound() {
        when(ingredientRepository.findById(1L)).thenReturn(Optional.empty());

        IngredientDto result = ingredientService.findById(1L);

        assertNull(result);
    }

    @Test
    public void testSave() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(1L);
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        IngredientDto result = ingredientService.save(ingredientDto);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testDelete() {
        ingredientService.delete(1L);
        // No exception means the test passed
    }
}
