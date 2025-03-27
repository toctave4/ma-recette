package com.toctave.ma_recette.compositions;

import com.toctave.ma_recette.ingredients.Ingredient;
import com.toctave.ma_recette.recettes.Recette;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Composition {
    @EmbeddedId
    private CompositionId id;
    private float quantite;
    private String unite;
}
