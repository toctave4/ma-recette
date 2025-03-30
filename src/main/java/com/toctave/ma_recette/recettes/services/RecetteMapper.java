package com.toctave.ma_recette.recettes.services;

import com.toctave.ma_recette.recettes.Recette;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RecetteMapper {
    RecetteMapper INSTANCE = Mappers.getMapper(RecetteMapper.class);

    RecetteDto toRecetteDto(Recette recette);
    Recette toRecette(RecetteDto recetteDto);
    List<RecetteDto> toRecetteDto(List<Recette> recettes);
    List<Recette> toRecettes(List<RecetteDto> recetteDtos);
}
