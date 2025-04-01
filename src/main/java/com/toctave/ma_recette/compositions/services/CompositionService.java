package com.toctave.ma_recette.compositions.services;

import com.toctave.ma_recette.compositions.Composition;
import com.toctave.ma_recette.compositions.CompositionId;
import com.toctave.ma_recette.compositions.repositories.CompositionRepository;
import com.toctave.ma_recette.ingredients.Ingredient;
import com.toctave.ma_recette.ingredients.repositories.IngredientRepository;
import com.toctave.ma_recette.recettes.Recette;
import com.toctave.ma_recette.recettes.repositories.RecetteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompositionService {
    private final CompositionRepository compositionRepository;
    private final RecetteRepository recetteRepository;
    private final IngredientRepository ingredientRepository;

    public CompositionService(CompositionRepository compositionRepository,
                              RecetteRepository recetteRepository,
                              IngredientRepository ingredientRepository) {
        this.compositionRepository = compositionRepository;
        this.recetteRepository = recetteRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<CompositionDto> findAll() {
        return CompositionMapper.INSTANCE.toCompositionDto(compositionRepository.findAll());
    }

    public List<CompositionDto> findAllByRecetteId(Long recetteId) {
        List<Composition> compositions = compositionRepository.findById_Recette_Id(recetteId);
        return CompositionMapper.INSTANCE.toCompositionDto(compositions);
    }

    public CompositionDto getCompositionById(Long recetteId, Long ingredientId) {
        Recette recette = recetteRepository.findById(recetteId)
                .orElseThrow(() -> new IllegalArgumentException("Recette not found with id: " + recetteId));
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with id: " + ingredientId));

        CompositionId compositionId = new CompositionId();
        compositionId.setRecette(recette);
        compositionId.setIngredient(ingredient);

        Composition composition = compositionRepository.findById(compositionId)
                .orElseThrow(() -> new IllegalArgumentException("Composition not found with recetteId: " + recetteId + " and ingredientId: " + ingredientId));

        return CompositionMapper.INSTANCE.toCompositionDto(composition);
    }

    public CompositionDto save(CompositionDto compositionDto) {
        recetteRepository.findById(compositionDto.getRecetteId())
                .orElseThrow(() -> new IllegalArgumentException("Recette not found with id: " + compositionDto.getRecetteId()));
        ingredientRepository.findById(compositionDto.getIngredientId())
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with id: " + compositionDto.getIngredientId()));

        Composition composition = CompositionMapper.INSTANCE.toComposition(compositionDto);
        Composition savedComposition = compositionRepository.save(composition);
        return CompositionMapper.INSTANCE.toCompositionDto(savedComposition);
    }
}
