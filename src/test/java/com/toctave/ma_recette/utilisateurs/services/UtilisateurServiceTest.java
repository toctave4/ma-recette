package com.toctave.ma_recette.utilisateurs.services;

import com.toctave.ma_recette.utilisateurs.Utilisateur;
import com.toctave.ma_recette.utilisateurs.repositories.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Utilisateur> utilisateurList = Arrays.asList(new Utilisateur(), new Utilisateur());
        when(utilisateurRepository.findAll()).thenReturn(utilisateurList);

        List<UtilisateurDto> result = utilisateurService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));

        UtilisateurDto result = utilisateurService.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindById_NotFound() {
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.empty());

        UtilisateurDto result = utilisateurService.findById(1L);

        assertNull(result);
    }

    @Test
    public void testSave() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1L);
        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);

        UtilisateurDto result = utilisateurService.save(utilisateurDto);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testDeleteById() {
        utilisateurService.deleteById(1L);
        // No exception means the test passed
    }
}
