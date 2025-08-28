package com.toctave.ma_recette.plan.repas.services;

import com.toctave.ma_recette.plan.repas.PlanRepas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PlanRepasMapper {
    PlanRepasMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(PlanRepasMapper.class);

    PlanRepasDto toPlanRepasDto(PlanRepas planRepas);

    @Mapping(target = "utilisateur", ignore = true) // Assuming you want to ignore this field for now
    PlanRepas toPlanRepas(PlanRepasDto planRepasDto);

    List<PlanRepasDto> toPlanRepasDto(List<PlanRepas> planRepasList);

    List<PlanRepas> toPlanRepas(List<PlanRepasDto> planRepasDtoList);
}
