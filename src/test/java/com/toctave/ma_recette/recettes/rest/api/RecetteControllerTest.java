package com.toctave.ma_recette.recettes.rest.api;

import com.toctave.ma_recette.recettes.services.RecetteDto;
import com.toctave.ma_recette.recettes.services.RecetteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecetteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecetteService recetteService;

    @InjectMocks
    private RecetteController recetteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recetteController).build();
    }

    @Test
    public void testGetAllRecettes() throws Exception {
        List<RecetteDto> recetteList = Arrays.asList(new RecetteDto(), new RecetteDto());
        when(recetteService.findAll()).thenReturn(recetteList);

        mockMvc.perform(get("/api/recettes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void testGetRecetteById() throws Exception {
        RecetteDto recetteDto = new RecetteDto();
        when(recetteService.findById(1L)).thenReturn(recetteDto);

        mockMvc.perform(get("/api/recettes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testGetRecetteById_NotFound() throws Exception {
        when(recetteService.findById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/recettes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateRecette() throws Exception {
        RecetteDto recetteDto = new RecetteDto();
        when(recetteService.save(any(RecetteDto.class))).thenReturn(recetteDto);

        mockMvc.perform(post("/api/recettes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateRecette() throws Exception {
        RecetteDto recetteDto = new RecetteDto();
        when(recetteService.findById(1L)).thenReturn(recetteDto);
        when(recetteService.save(any(RecetteDto.class))).thenReturn(recetteDto);

        mockMvc.perform(put("/api/recettes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateRecette_NotFound() throws Exception {
        when(recetteService.findById(1L)).thenReturn(null);

        mockMvc.perform(put("/api/recettes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteRecette() throws Exception {
        RecetteDto recetteDto = new RecetteDto();
        when(recetteService.findById(1L)).thenReturn(recetteDto);

        mockMvc.perform(delete("/api/recettes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteRecette_NotFound() throws Exception {
        when(recetteService.findById(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/recettes/1"))
                .andExpect(status().isNotFound());
    }
}
