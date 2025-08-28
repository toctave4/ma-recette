package com.toctave.ma_recette.compositions;

import com.toctave.ma_recette.ingredients.Ingredient;
import com.toctave.ma_recette.recettes.Recette;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Composition {
    @EmbeddedId
    private CompositionId id;
    private Double quantite;
    private String unite;
}
