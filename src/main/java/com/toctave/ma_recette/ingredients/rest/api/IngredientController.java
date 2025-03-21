package com.toctave.ma_recette.ingredients.rest.api;

import com.toctave.ma_recette.ingredients.services.IngredientDto;
import com.toctave.ma_recette.ingredients.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable Long id) {
        IngredientDto ingredientDto = ingredientService.findById(id);
        return ingredientDto != null ? ResponseEntity.ok(ingredientDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public IngredientDto createIngredient(@RequestBody IngredientDto ingredientDto) {
        return ingredientService.save(ingredientDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> updateIngredient(@PathVariable Long id, @RequestBody IngredientDto ingredientDto) {
        IngredientDto updatedIngredientDto = ingredientService.findById(id);
        if (updatedIngredientDto == null) {
            return ResponseEntity.notFound().build();
        }
        updatedIngredientDto.setNom(ingredientDto.getNom());
        updatedIngredientDto.setTypeIngredient(ingredientDto.getTypeIngredient());
        updatedIngredientDto.setValeursNutritionnelles(ingredientDto.getValeursNutritionnelles());
        return ResponseEntity.ok(ingredientService.save(updatedIngredientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        IngredientDto ingredientDto = ingredientService.findById(id);
        if (ingredientDto == null) {
            return ResponseEntity.notFound().build();
        }
        ingredientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
