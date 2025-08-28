package com.toctave.ma_recette.compositions.services;

import com.toctave.ma_recette.compositions.Composition;
import com.toctave.ma_recette.compositions.CompositionId;
import com.toctave.ma_recette.compositions.repositories.CompositionRepository;
import com.toctave.ma_recette.ingredients.Ingredient;
import com.toctave.ma_recette.ingredients.repositories.IngredientRepository;
import com.toctave.ma_recette.recettes.Recette;
import com.toctave.ma_recette.recettes.repositories.RecetteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CompositionServiceTest {

    @Mock
    private CompositionRepository compositionRepository;

    @Mock
    private RecetteRepository recetteRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private CompositionService compositionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Composition> compositions = getCompositions();
        when(compositionRepository.findAll()).thenReturn(compositions);

        List<CompositionDto> result = compositionService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindAllByRecetteId() {
        List<Composition> compositions = getCompositions();
        when(compositionRepository.findById_Recette_Id(1L)).thenReturn(compositions);

        List<CompositionDto> result = compositionService.findAllByRecetteId(1L);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetCompositionById() {
        Recette recette = new Recette();
        recette.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        CompositionId compositionId = new CompositionId();
        compositionId.setRecette(recette);
        compositionId.setIngredient(ingredient);
        Composition composition = new Composition();
        composition.setId(compositionId);

        when(recetteRepository.findById(1L)).thenReturn(Optional.of(recette));
        when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
        when(compositionRepository.findById(any(CompositionId.class))).thenReturn(Optional.of(composition));

        CompositionDto result = compositionService.getCompositionById(1L, 2L);

        assertEquals(1L, result.getRecetteId());
        assertEquals(2L, result.getIngredientId());
    }

    @Test
    public void testGetCompositionById_NotFound() {
        when(recetteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            compositionService.getCompositionById(1L, 2L);
        });
    }

    @Test
    public void testSave() {
        CompositionDto compositionDto = new CompositionDto();
        compositionDto.setRecetteId(1L);
        compositionDto.setIngredientId(2L);
        Recette recette = new Recette();
        recette.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        Composition composition = new Composition();
        CompositionId compositionId = new CompositionId();
        compositionId.setRecette(recette);
        compositionId.setIngredient(ingredient);
        composition.setId(compositionId);

        when(recetteRepository.findById(1L)).thenReturn(Optional.of(recette));
        when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
        when(compositionRepository.save(any(Composition.class))).thenReturn(composition);

        CompositionDto result = compositionService.save(compositionDto);

        assertEquals(1L, result.getRecetteId());
        assertEquals(2L, result.getIngredientId());
    }

    private List<Composition> getCompositions() {
        Composition composition1 = new Composition();
        composition1.setId(new CompositionId());
        composition1.getId().setRecette(new Recette());
        composition1.getId().setIngredient(new Ingredient());
        composition1.setQuantite(100.0);
        composition1.setUnite("g");

        Composition composition2 = new Composition();
        composition2.setId(new CompositionId());
        composition2.getId().setRecette(new Recette());
        composition2.getId().setIngredient(new Ingredient());
        composition2.setQuantite(200.0);
        composition2.setUnite("ml");

        return Arrays.asList(composition1, composition2);
    }
}
