package com.toctave.ma_recette.plan.repas.services;

import com.toctave.ma_recette.plan.repas.PlanRepas;
import com.toctave.ma_recette.recettes.Recette;
import com.toctave.ma_recette.recettes.services.RecetteDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanRepasMapperTest {

    private final PlanRepasMapper planRepasMapper = PlanRepasMapper.INSTANCE;

    @Test
    public void testToPlanRepasDto() {
        PlanRepas planRepas = new PlanRepas();
        planRepas.setId(1L);
        planRepas.setNom("Weekly Plan");
        planRepas.setRecettes(getRecettes());

        PlanRepasDto planRepasDto = planRepasMapper.toPlanRepasDto(planRepas);

        assertEquals(1L, planRepasDto.getId());
        assertEquals("Weekly Plan", planRepasDto.getNom());
    }

    @Test
    public void testToPlanRepas() {
        PlanRepasDto planRepasDto = new PlanRepasDto();
        planRepasDto.setId(1L);
        planRepasDto.setNom("Weekly Plan");
        planRepasDto.setRecettes(getRecetteDtos());

        PlanRepas planRepas = planRepasMapper.toPlanRepas(planRepasDto);

        assertEquals(1L, planRepas.getId());
        assertEquals("Weekly Plan", planRepas.getNom());
    }

    @Test
    public void testToPlanRepasDtoList() {
        PlanRepas planRepas1 = new PlanRepas();
        planRepas1.setId(1L);
        planRepas1.setNom("Weekly Plan 1");
        planRepas1.setRecettes(getRecettes());

        PlanRepas planRepas2 = new PlanRepas();
        planRepas2.setId(2L);
        planRepas2.setNom("Weekly Plan 2");
        planRepas2.setRecettes(getRecettes());

        List<PlanRepas> planRepasList = Arrays.asList(planRepas1, planRepas2);
        List<PlanRepasDto> planRepasDtoList = planRepasMapper.toPlanRepasDto(planRepasList);

        assertEquals(2, planRepasDtoList.size());
        assertEquals(1L, planRepasDtoList.get(0).getId());
        assertEquals("Weekly Plan 1", planRepasDtoList.get(0).getNom());
        assertEquals(2L, planRepasDtoList.get(1).getId());
        assertEquals("Weekly Plan 2", planRepasDtoList.get(1).getNom());
    }

    @Test
    public void testToPlanRepasList() {
        PlanRepasDto planRepasDto1 = new PlanRepasDto();
        planRepasDto1.setId(1L);
        planRepasDto1.setNom("Weekly Plan 1");
        planRepasDto1.setRecettes(getRecetteDtos());

        PlanRepasDto planRepasDto2 = new PlanRepasDto();
        planRepasDto2.setId(2L);
        planRepasDto2.setNom("Weekly Plan 2");
        planRepasDto2.setRecettes(getRecetteDtos());

        List<PlanRepasDto> planRepasDtoList = Arrays.asList(planRepasDto1, planRepasDto2);
        List<PlanRepas> planRepasList = planRepasMapper.toPlanRepas(planRepasDtoList);

        assertEquals(2, planRepasList.size());
        assertEquals(1L, planRepasList.get(0).getId());
        assertEquals("Weekly Plan 1", planRepasList.get(0).getNom());
        assertEquals(2L, planRepasList.get(1).getId());
        assertEquals("Weekly Plan 2", planRepasList.get(1).getNom());
    }

    private List<Recette> getRecettes() {
        Recette recette1 = new Recette();
        recette1.setId(1L);
        recette1.setTitre("Recette 1");

        Recette recette2 = new Recette();
        recette2.setId(2L);
        recette2.setTitre("Recette 2");

        return Arrays.asList(recette1, recette2);
    }

    private List<RecetteDto> getRecetteDtos() {
        RecetteDto recetteDto1 = new RecetteDto();
        recetteDto1.setId(1L);
        recetteDto1.setTitre("Recette 1");

        RecetteDto recetteDto2 = new RecetteDto();
        recetteDto2.setId(2L);
        recetteDto2.setTitre("Recette 2");

        return Arrays.asList(recetteDto1, recetteDto2);
    }
}
