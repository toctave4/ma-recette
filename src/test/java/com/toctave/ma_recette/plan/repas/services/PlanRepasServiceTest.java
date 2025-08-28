package com.toctave.ma_recette.plan.repas.services;

import com.toctave.ma_recette.plan.repas.PlanRepas;
import com.toctave.ma_recette.plan.repas.repositories.PlanRepasRepository;
import com.toctave.ma_recette.recettes.Recette;
import com.toctave.ma_recette.recettes.services.RecetteDto;
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

public class PlanRepasServiceTest {

    @Mock
    private PlanRepasRepository planRepasRepository;

    @InjectMocks
    private PlanRepasService planRepasService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<PlanRepas> planRepasList = Arrays.asList(new PlanRepas(), new PlanRepas());
        when(planRepasRepository.findAll()).thenReturn(planRepasList);

        List<PlanRepasDto> result = planRepasService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetPlanRepasById() {
        PlanRepas planRepas = new PlanRepas();
        planRepas.setId(1L);
        when(planRepasRepository.findById(1L)).thenReturn(Optional.of(planRepas));

        PlanRepasDto result = planRepasService.getPlanRepasById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetPlanRepasById_NotFound() {
        when(planRepasRepository.findById(1L)).thenReturn(Optional.empty());

        PlanRepasDto result = planRepasService.getPlanRepasById(1L);

        assertNull(result);
    }

    @Test
    public void testSave() {
        PlanRepas planRepas = new PlanRepas();
        planRepas.setId(1L);
        PlanRepasDto planRepasDto = new PlanRepasDto();
        planRepasDto.setId(1L);
        when(planRepasRepository.save(any(PlanRepas.class))).thenReturn(planRepas);

        PlanRepasDto result = planRepasService.save(planRepasDto);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testDelete() {
        planRepasService.delete(1L);
        // No exception means the test passed
    }

    @Test
    public void testGetRecettesByPlanRepasId() {
        PlanRepas planRepas = new PlanRepas();
        planRepas.setId(1L);
        planRepas.setRecettes(Arrays.asList(new Recette(), new Recette()));
        when(planRepasRepository.findById(1L)).thenReturn(Optional.of(planRepas));

        List<RecetteDto> result = planRepasService.getRecettesByPlanRepasId(1L);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetRecettesByPlanRepasId_NotFound() {
        when(planRepasRepository.findById(1L)).thenReturn(Optional.empty());

        List<RecetteDto> result = planRepasService.getRecettesByPlanRepasId(1L);

        assertNull(result);
    }
}
