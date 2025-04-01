package com.toctave.ma_recette.plan.repas.repositories;

import com.toctave.ma_recette.plan.repas.PlanRepas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepasRepository extends JpaRepository<PlanRepas, Long> {
}
