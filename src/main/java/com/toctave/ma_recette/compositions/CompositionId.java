package com.toctave.ma_recette.compositions;

import com.toctave.ma_recette.ingredients.Ingredient;
import com.toctave.ma_recette.recettes.Recette;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class CompositionId {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Recette recette;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ingredient ingredient;
}
