package com.toctave.ma_recette.compositions.repositories;

import com.toctave.ma_recette.compositions.Composition;
import com.toctave.ma_recette.compositions.CompositionId;
import com.toctave.ma_recette.recettes.Recette;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionRepository extends JpaRepository<Composition, CompositionId> {
    CompositionId id(CompositionId id);

    List<Composition> findById_Recette_Id(Long idRecetteId);
}
