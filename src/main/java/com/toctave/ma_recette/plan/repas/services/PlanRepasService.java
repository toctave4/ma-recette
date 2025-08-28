package com.toctave.ma_recette.plan.repas.services;

import com.toctave.ma_recette.recettes.services.RecetteDto;
import com.toctave.ma_recette.recettes.services.RecetteMapper;
import org.springframework.stereotype.Service;
import com.toctave.ma_recette.plan.repas.PlanRepas;
import com.toctave.ma_recette.plan.repas.repositories.PlanRepasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanRepasService {
    private final PlanRepasRepository planRepasRepository;

    public PlanRepasService(PlanRepasRepository planRepasRepository) {
        this.planRepasRepository = planRepasRepository;
    }

    public List<PlanRepasDto> findAll() {
        return PlanRepasMapper.INSTANCE.toPlanRepasDto(planRepasRepository.findAll());
    }

    public PlanRepasDto getPlanRepasById(Long id) {
        Optional<PlanRepas> optionalPlanRepas = planRepasRepository.findById(id);
        return optionalPlanRepas.map(PlanRepasMapper.INSTANCE::toPlanRepasDto).orElse(null);
    }

    public PlanRepasDto save(PlanRepasDto planRepasDto) {
        PlanRepas planRepas = PlanRepasMapper.INSTANCE.toPlanRepas(planRepasDto);
        PlanRepas savedPlanRepas = planRepasRepository.save(planRepas);
        return PlanRepasMapper.INSTANCE.toPlanRepasDto(savedPlanRepas);
    }

    public void delete(Long id) {
        planRepasRepository.deleteById(id);
    }

    public List<RecetteDto> getRecettesByPlanRepasId(Long id) {
        Optional<PlanRepas> optionalPlanRepas = planRepasRepository.findById(id);
        if (optionalPlanRepas.isPresent()) {
            PlanRepas planRepas = optionalPlanRepas.get();
            return RecetteMapper.INSTANCE.toRecetteDto(planRepas.getRecettes());
        }
        return null;
    }
}
