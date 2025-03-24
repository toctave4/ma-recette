package com.toctave.ma_recette.ingredients.services;

import com.toctave.ma_recette.ingredients.Ingredient;
import com.toctave.ma_recette.ingredients.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientDto> findAll() {
        return IngredientMapper.INSTANCE.toIngredientDto(ingredientRepository.findAll());
    }

    public IngredientDto findById(long id) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        return optionalIngredient.map(IngredientMapper.INSTANCE::toIngredientDto).orElse(null);
    }

    public IngredientDto save(IngredientDto ingredientDto) {
        Ingredient ingredient = IngredientMapper.INSTANCE.toIngredient(ingredientDto);
        return IngredientMapper.INSTANCE.toIngredientDto(ingredientRepository.save(ingredient));
    }

    public void delete(long id) {
        ingredientRepository.deleteById(id);
    }

}
