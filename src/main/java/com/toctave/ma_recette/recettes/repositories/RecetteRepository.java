package com.toctave.ma_recette.recettes.repositories;

import com.toctave.ma_recette.recettes.Recette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetteRepository extends JpaRepository<Recette, Long> {
}
