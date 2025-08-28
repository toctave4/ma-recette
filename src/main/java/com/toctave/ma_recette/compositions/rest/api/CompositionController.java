package com.toctave.ma_recette.compositions.rest.api;

import com.toctave.ma_recette.compositions.Composition;
import com.toctave.ma_recette.compositions.CompositionId;
import com.toctave.ma_recette.compositions.services.CompositionDto;
import com.toctave.ma_recette.compositions.services.CompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/compositions")
public class CompositionController {

    @Autowired
    private CompositionService compositionService;

    @GetMapping
    public List<CompositionDto> getAllCompositions() {
        return compositionService.findAll();
    }

    @GetMapping("/{recetteId}")
    public List<CompositionDto> getCompositionsByRecetteId(@PathVariable Long recetteId) {
        return compositionService.findAllByRecetteId(recetteId);
    }

    @GetMapping("/{recetteId}/{ingredientId}")
    public CompositionDto getCompositionById(@PathVariable Long recetteId, @PathVariable Long ingredientId) {
        return compositionService.getCompositionById(recetteId, ingredientId);
    }

    @PostMapping
    public CompositionDto createComposition(@RequestBody CompositionDto compositionDto) {
        return compositionService.save(compositionDto);
    }
}
