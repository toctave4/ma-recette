package com.toctave.ma_recette.recettes.services;

import com.toctave.ma_recette.recettes.Recette;
import com.toctave.ma_recette.recettes.repositories.RecetteRepository;
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

public class RecetteServiceTest {

    @Mock
    private RecetteRepository recetteRepository;

    @InjectMocks
    private RecetteService recetteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Recette> recetteList = Arrays.asList(new Recette(), new Recette());
        when(recetteRepository.findAll()).thenReturn(recetteList);

        List<RecetteDto> result = recetteService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Recette recette = new Recette();
        recette.setId(1L);
        when(recetteRepository.findById(1L)).thenReturn(Optional.of(recette));

        RecetteDto result = recetteService.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindById_NotFound() {
        when(recetteRepository.findById(1L)).thenReturn(Optional.empty());

        RecetteDto result = recetteService.findById(1L);

        assertNull(result);
    }

    @Test
    public void testSave() {
        Recette recette = new Recette();
        recette.setId(1L);
        RecetteDto recetteDto = new RecetteDto();
        recetteDto.setId(1L);
        when(recetteRepository.save(any(Recette.class))).thenReturn(recette);

        RecetteDto result = recetteService.save(recetteDto);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testDelete() {
        recetteService.delete(1L);
        // No exception means the test passed
    }
}
