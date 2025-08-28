package com.toctave.ma_recette.plan.repas.repositories;

import com.toctave.ma_recette.plan.repas.PlanRepas;
import com.toctave.ma_recette.recettes.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepasRepository extends JpaRepository<PlanRepas, Long> {
}
