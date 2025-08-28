package com.toctave.ma_recette.plan.repas.rest.api;

import com.toctave.ma_recette.recettes.services.RecetteDto;
import com.toctave.ma_recette.recettes.services.RecetteService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;
import com.toctave.ma_recette.plan.repas.services.PlanRepasDto;
import com.toctave.ma_recette.plan.repas.services.PlanRepasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plan-repas")
public class PlanRepasController {
    private final PlanRepasService planRepasService;
    private final RecetteService recetteService;

    public PlanRepasController(PlanRepasService planRepasService, RecetteService recetteService) {
        this.planRepasService = planRepasService;
        this.recetteService = recetteService;
    }

    @GetMapping
    public List<PlanRepasDto> getAllPlanRepas() {
        return planRepasService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanRepasDto> getPlanRepasById(@PathVariable Long id) {
        PlanRepasDto planRepasDto = planRepasService.getPlanRepasById(id);
        return planRepasDto != null ? ResponseEntity.ok(planRepasDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PlanRepasDto> createPlanRepas(@RequestBody PlanRepasDto planRepasDto) {
        PlanRepasDto savedPlanRepasDto = planRepasService.save(planRepasDto);
        return savedPlanRepasDto != null ? ResponseEntity.ok(savedPlanRepasDto) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanRepasDto> updatePlanRepas(@PathVariable Long id, @RequestBody PlanRepasDto planRepasDto) {
        PlanRepasDto existingPlanRepasDto = planRepasService.getPlanRepasById(id);
        if (existingPlanRepasDto == null) {
            return ResponseEntity.notFound().build();
        }
        existingPlanRepasDto.setObjectif(planRepasDto.getObjectif());
        existingPlanRepasDto.setRecettes(planRepasDto.getRecettes());
        existingPlanRepasDto.setDate(planRepasDto.getDate());
        return ResponseEntity.ok(planRepasService.save(existingPlanRepasDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanRepas(@PathVariable Long id) {
        Assert.notNull(id, "ID must not be null");
        if (planRepasService.getPlanRepasById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        planRepasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/recettes")
    public ResponseEntity<List<RecetteDto>> getRecettesByPlanRepasId(@PathVariable Long id) {
        List<RecetteDto> recettes = planRepasService.getRecettesByPlanRepasId(id);
        return recettes != null ? ResponseEntity.ok(recettes) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/recettes")
    public ResponseEntity<RecetteDto> addRecetteToPlanRepas(@PathVariable Long id, @RequestBody RecetteDto recetteDto) {
        PlanRepasDto planRepasDto = planRepasService.getPlanRepasById(id);
        if (planRepasDto == null) {
            return ResponseEntity.notFound().build();
        }
        if (recetteDto == null) {
            return ResponseEntity.badRequest().build();
        }
        List<RecetteDto> recettes = planRepasDto.getRecettes();
        recettes.add(recetteDto);
        recetteService.save(recetteDto);
        planRepasService.save(planRepasDto);
        return ResponseEntity.ok(recetteDto);
    }
}
