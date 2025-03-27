package com.toctave.ma_recette.compositions;

import com.toctave.ma_recette.ingredients.Ingredient;
import com.toctave.ma_recette.recettes.Recette;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class CompositionId {
    @OneToOne
    private Recette recette;

    @OneToOne
    private Ingredient ingredient;
}
