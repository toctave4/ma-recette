package com.toctave.ma_recette.plan.repas.services;
import lombok.Data;
import com.toctave.ma_recette.recettes.services.RecetteDto;
import java.util.Date;
import java.util.List;

@Data
public class PlanRepasDto {
    private Long id;
    private Long utilisateurId;
    private List<RecetteDto> recettes;
    private Date date;
    private String objectif;
}
