package com.toctave.ma_recette.utilisateurs.services;

import com.toctave.ma_recette.utilisateurs.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    UtilisateurDto toUtilisateurDto(Utilisateur utilisateur);
    List<UtilisateurDto> toUtilisateurDto(List<Utilisateur> utilisateurs);
    Utilisateur toUtilisateur(UtilisateurDto utilisateurDto);
    List<Utilisateur> toUtilisateur(List<UtilisateurDto> utilisateurDtos);
}
