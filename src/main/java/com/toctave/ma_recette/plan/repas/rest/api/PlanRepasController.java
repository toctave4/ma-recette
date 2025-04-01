package com.toctave.ma_recette.plan.repas.rest.api;

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

    public PlanRepasController(PlanRepasService planRepasService) {
        this.planRepasService = planRepasService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanRepas(@PathVariable Long id) {
        planRepasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
