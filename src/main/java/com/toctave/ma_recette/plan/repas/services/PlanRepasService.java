package com.toctave.ma_recette.plan.repas.services;

import com.toctave.ma_recette.recettes.services.RecetteDto;
import com.toctave.ma_recette.recettes.services.RecetteMapper;
import com.toctave.ma_recette.utilisateurs.Utilisateur;
import com.toctave.ma_recette.utilisateurs.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;
import com.toctave.ma_recette.plan.repas.PlanRepas;
import com.toctave.ma_recette.plan.repas.repositories.PlanRepasRepository;
import com.toctave.ma_recette.recettes.repositories.RecetteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanRepasService {
    private final PlanRepasRepository planRepasRepository;
    private final RecetteRepository recetteRepository;
    private final UtilisateurRepository utilisateurRepository;

    public PlanRepasService(PlanRepasRepository planRepasRepository, RecetteRepository recetteRepository, UtilisateurRepository utilisateurRepository) {
        this.planRepasRepository = planRepasRepository;
        this.recetteRepository = recetteRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<PlanRepasDto> findAll() {
        return PlanRepasMapper.INSTANCE.toPlanRepasDto(planRepasRepository.findAll());
    }

    public PlanRepasDto getPlanRepasById(Long id) {
        Optional<PlanRepas> optionalPlanRepas = planRepasRepository.findById(id);
        return optionalPlanRepas.map(PlanRepasMapper.INSTANCE::toPlanRepasDto).orElse(null);
    }

    public PlanRepasDto save(PlanRepasDto planRepasDto) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(planRepasDto.getUtilisateurId());
        Utilisateur utilisateur = utilisateurOptional.orElse(null);
        if (utilisateur == null) {
            return null;
        }
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
