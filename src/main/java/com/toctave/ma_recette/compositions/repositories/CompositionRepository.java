package com.toctave.ma_recette.compositions.repositories;

import com.toctave.ma_recette.compositions.Composition;
import com.toctave.ma_recette.compositions.CompositionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionRepository extends JpaRepository<Composition, CompositionId> {
    CompositionId id(CompositionId id);
}
