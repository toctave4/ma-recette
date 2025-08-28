package com.toctave.ma_recette.recettes.services;

import com.toctave.ma_recette.recettes.Recette;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecetteMapperTest {

    private final RecetteMapper recetteMapper = RecetteMapper.INSTANCE;

    @Test
    public void testToRecetteDto() {
        Recette recette = new Recette();
        recette.setId(1L);
        recette.setTitre("Recipe 1");

        RecetteDto recetteDto = recetteMapper.toRecetteDto(recette);

        assertEquals(1L, recetteDto.getId());
        assertEquals("Recipe 1", recetteDto.getTitre());
    }

    @Test
    public void testToRecette() {
        RecetteDto recetteDto = new RecetteDto();
        recetteDto.setId(1L);
        recetteDto.setTitre("Recipe 1");

        Recette recette = recetteMapper.toRecette(recetteDto);

        assertEquals(1L, recette.getId());
        assertEquals("Recipe 1", recette.getTitre());
    }

    @Test
    public void testToRecetteDtoList() {
        Recette recette1 = new Recette();
        recette1.setId(1L);
        recette1.setTitre("Recipe 1");

        Recette recette2 = new Recette();
        recette2.setId(2L);
        recette2.setTitre("Recipe 2");

        List<Recette> recetteList = Arrays.asList(recette1, recette2);
        List<RecetteDto> recetteDtoList = recetteMapper.toRecetteDto(recetteList);

        assertEquals(2, recetteDtoList.size());
        assertEquals(1L, recetteDtoList.get(0).getId());
        assertEquals("Recipe 1", recetteDtoList.get(0).getTitre());
        assertEquals(2L, recetteDtoList.get(1).getId());
        assertEquals("Recipe 2", recetteDtoList.get(1).getTitre());
    }

    @Test
    public void testToRecettesList() {
        RecetteDto recetteDto1 = new RecetteDto();
        recetteDto1.setId(1L);
        recetteDto1.setTitre("Recipe 1");

        RecetteDto recetteDto2 = new RecetteDto();
        recetteDto2.setId(2L);
        recetteDto2.setTitre("Recipe 2");

        List<RecetteDto> recetteDtoList = Arrays.asList(recetteDto1, recetteDto2);
        List<Recette> recetteList = recetteMapper.toRecettes(recetteDtoList);

        assertEquals(2, recetteList.size());
        assertEquals(1L, recetteList.get(0).getId());
        assertEquals("Recipe 1", recetteList.get(0).getTitre());
        assertEquals(2L, recetteList.get(1).getId());
        assertEquals("Recipe 2", recetteList.get(1).getTitre());
    }
}
