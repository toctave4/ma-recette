package com.toctave.ma_recette.utilisateurs.services;

import com.toctave.ma_recette.utilisateurs.Utilisateur;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilisateurMapperTest {

    private final UtilisateurMapper utilisateurMapper = UtilisateurMapper.INSTANCE;

    @Test
    public void testToUtilisateurDto() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("John");
        utilisateur.setPrenom("Doe");

        UtilisateurDto utilisateurDto = utilisateurMapper.toUtilisateurDto(utilisateur);

        assertEquals(1L, utilisateurDto.getId());
        assertEquals("John", utilisateurDto.getNom());
        assertEquals("Doe", utilisateurDto.getPrenom());
    }

    @Test
    public void testToUtilisateur() {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1L);
        utilisateurDto.setNom("John");
        utilisateurDto.setPrenom("Doe");

        Utilisateur utilisateur = utilisateurMapper.toUtilisateur(utilisateurDto);

        assertEquals(1L, utilisateur.getId());
        assertEquals("John", utilisateur.getNom());
        assertEquals("Doe", utilisateur.getPrenom());
    }

    @Test
    public void testToUtilisateurDtoList() {
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setId(1L);
        utilisateur1.setNom("John");

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setId(2L);
        utilisateur2.setNom("Jane");

        List<Utilisateur> utilisateurList = Arrays.asList(utilisateur1, utilisateur2);
        List<UtilisateurDto> utilisateurDtoList = utilisateurMapper.toUtilisateurDto(utilisateurList);

        assertEquals(2, utilisateurDtoList.size());
        assertEquals(1L, utilisateurDtoList.get(0).getId());
        assertEquals("John", utilisateurDtoList.get(0).getNom());
        assertEquals(2L, utilisateurDtoList.get(1).getId());
        assertEquals("Jane", utilisateurDtoList.get(1).getNom());
    }

    @Test
    public void testToUtilisateurList() {
        UtilisateurDto utilisateurDto1 = new UtilisateurDto();
        utilisateurDto1.setId(1L);
        utilisateurDto1.setNom("John");

        UtilisateurDto utilisateurDto2 = new UtilisateurDto();
        utilisateurDto2.setId(2L);
        utilisateurDto2.setNom("Jane");

        List<UtilisateurDto> utilisateurDtoList = Arrays.asList(utilisateurDto1, utilisateurDto2);
        List<Utilisateur> utilisateurList = utilisateurMapper.toUtilisateur(utilisateurDtoList);

        assertEquals(2, utilisateurList.size());
        assertEquals(1L, utilisateurList.get(0).getId());
        assertEquals("John", utilisateurList.get(0).getNom());
        assertEquals(2L, utilisateurList.get(1).getId());
        assertEquals("Jane", utilisateurList.get(1).getNom());
    }
}
