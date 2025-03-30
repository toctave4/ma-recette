package com.toctave.ma_recette.recettes.rest.api;

import com.toctave.ma_recette.recettes.services.RecetteDto;
import com.toctave.ma_recette.recettes.services.RecetteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recettes")
public class RecetteController {
    private final RecetteService recetteService;

    public RecetteController(RecetteService recetteService) {
        this.recetteService = recetteService;
    }

    @GetMapping
    public List<RecetteDto> getAllRecettes() {
        return recetteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetteDto> getRecetteById(@PathVariable Long id) {
        RecetteDto recetteDto = recetteService.findById(id);
        return recetteDto != null ? ResponseEntity.ok(recetteDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public RecetteDto createRecette(@RequestBody RecetteDto recetteDto) {
        return recetteService.save(recetteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetteDto> updateRecette(@PathVariable Long id, @RequestBody RecetteDto recetteDto) {
        RecetteDto updatedRecetteDto = recetteService.findById(id);
        if (updatedRecetteDto == null) {
            return ResponseEntity.notFound().build();
        }
        updatedRecetteDto.setTitre(recetteDto.getTitre());
        updatedRecetteDto.setDescription(recetteDto.getDescription());
        updatedRecetteDto.setIngredients(recetteDto.getIngredients());
        updatedRecetteDto.setInstructions(recetteDto.getInstructions());
        return ResponseEntity.ok(recetteService.save(updatedRecetteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecette(@PathVariable Long id) {
        RecetteDto recetteDto = recetteService.findById(id);
        if (recetteDto == null) {
            return ResponseEntity.notFound().build();
        }
        recetteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
