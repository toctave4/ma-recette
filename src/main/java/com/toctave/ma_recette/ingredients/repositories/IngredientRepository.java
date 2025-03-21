package com.toctave.ma_recette.ingredients.repositories;

import com.toctave.ma_recette.ingredients.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
