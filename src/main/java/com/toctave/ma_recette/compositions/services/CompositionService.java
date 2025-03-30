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

    public CompositionDto getCompositionById(Long recetteId, Long ingredientId) {
        Optional<Recette> recetteOptional = recetteRepository.findById(recetteId);
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);
        Recette recette = recetteOptional.orElse(null);
        Ingredient ingredient = ingredientOptional.orElse(null);
        if (recette == null || ingredient == null) {
            return null;
        }
        CompositionId compositionId = new CompositionId();
        compositionId.setRecette(recette);
        compositionId.setIngredient(ingredient);
        Optional<Composition> optionalComposition = compositionRepository.findById(compositionId);
        return optionalComposition.map(CompositionMapper.INSTANCE::toCompositionDto).orElse(null);
    }

    public CompositionDto save(CompositionDto compositionDto) {
        Optional<Recette> recetteOptional = recetteRepository.findById(compositionDto.getRecetteId());
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(compositionDto.getIngredientId());
        Recette recette = recetteOptional.orElse(null);
        Ingredient ingredient = ingredientOptional.orElse(null);
        if (recette == null || ingredient == null) {
            return null;
        }
        Composition composition = CompositionMapper.INSTANCE.toComposition(compositionDto);
        Composition savedComposition = compositionRepository.save(composition);
        return CompositionMapper.INSTANCE.toCompositionDto(savedComposition);
    }
}
