package com.toctave.ma_recette.compositions.services;

import com.toctave.ma_recette.compositions.Composition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CompositionMapper {
    CompositionMapper INSTANCE = Mappers.getMapper(CompositionMapper.class);

    @Mapping(target = "recetteId", expression = "java(composition.getId().getRecette().getId())")
    @Mapping(target = "ingredientId", expression = "java(composition.getId().getIngredient().getId())")
    CompositionDto toCompositionDto(Composition composition);

    @Mapping(target = "id.recette.id", source = "recetteId")
    @Mapping(target = "id.ingredient.id", source = "ingredientId")
    Composition toComposition(CompositionDto compositionDto);

    List<CompositionDto> toCompositionDto(List<Composition> compositions);

    List<Composition> toComposition(List<CompositionDto> compositionDtos);
}
