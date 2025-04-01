package com.toctave.ma_recette.plan.repas.services;

import com.toctave.ma_recette.plan.repas.PlanRepas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PlanRepasMapper {
    PlanRepasMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(PlanRepasMapper.class);

    @Mapping(target = "utilisateurId", source = "utilisateur.id")
    PlanRepasDto toPlanRepasDto(PlanRepas planRepas);

    PlanRepas toPlanRepas(PlanRepasDto planRepasDto);

    List<PlanRepasDto> toPlanRepasDto(List<PlanRepas> planRepasList);

    List<PlanRepas> toPlanRepas(List<PlanRepasDto> planRepasDtoList);
}
