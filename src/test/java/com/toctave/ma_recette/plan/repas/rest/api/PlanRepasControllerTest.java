package com.toctave.ma_recette.plan.repas.rest.api;

import com.toctave.ma_recette.plan.repas.services.PlanRepasDto;
import com.toctave.ma_recette.plan.repas.services.PlanRepasService;
import com.toctave.ma_recette.recettes.services.RecetteDto;
import com.toctave.ma_recette.recettes.services.RecetteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PlanRepasControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlanRepasService planRepasService;

    @Mock
    private RecetteService recetteService;

    @InjectMocks
    private PlanRepasController planRepasController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(planRepasController).build();
    }

    @Test
    public void testGetAllPlanRepas() throws Exception {
        List<PlanRepasDto> planRepasList = Arrays.asList(new PlanRepasDto(), new PlanRepasDto());
        when(planRepasService.findAll()).thenReturn(planRepasList);

        mockMvc.perform(get("/api/plan-repas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void testGetPlanRepasById() throws Exception {
        PlanRepasDto planRepasDto = new PlanRepasDto();
        when(planRepasService.getPlanRepasById(1L)).thenReturn(planRepasDto);

        mockMvc.perform(get("/api/plan-repas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testGetPlanRepasById_NotFound() throws Exception {
        when(planRepasService.getPlanRepasById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/plan-repas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePlanRepas() throws Exception {
        PlanRepasDto planRepasDto = new PlanRepasDto();
        when(planRepasService.save(any(PlanRepasDto.class))).thenReturn(planRepasDto);

        mockMvc.perform(post("/api/plan-repas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdatePlanRepas() throws Exception {
        PlanRepasDto planRepasDto = new PlanRepasDto();
        when(planRepasService.getPlanRepasById(1L)).thenReturn(planRepasDto);
        when(planRepasService.save(any(PlanRepasDto.class))).thenReturn(planRepasDto);

        mockMvc.perform(put("/api/plan-repas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdatePlanRepas_NotFound() throws Exception {
        when(planRepasService.getPlanRepasById(1L)).thenReturn(null);

        mockMvc.perform(put("/api/plan-repas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePlanRepas() throws Exception {
        PlanRepasDto planRepasDto = new PlanRepasDto();
        when(planRepasService.getPlanRepasById(1L)).thenReturn(planRepasDto);

        mockMvc.perform(delete("/api/plan-repas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletePlanRepas_NotFound() throws Exception {
        when(planRepasService.getPlanRepasById(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/plan-repas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetRecettesByPlanRepasId_Found() {
        Long planRepasId = 1L;
        List<RecetteDto> recettes = Arrays.asList(new RecetteDto(), new RecetteDto());
        when(planRepasService.getRecettesByPlanRepasId(planRepasId)).thenReturn(recettes);

        ResponseEntity<List<RecetteDto>> response = planRepasController.getRecettesByPlanRepasId(planRepasId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetRecettesByPlanRepasId_NotFound() {
        Long planRepasId = 1L;
        when(planRepasService.getRecettesByPlanRepasId(planRepasId)).thenReturn(null);

        ResponseEntity<List<RecetteDto>> response = planRepasController.getRecettesByPlanRepasId(planRepasId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testAddRecetteToPlanRepas_Success() {
        Long planRepasId = 1L;
        RecetteDto recetteDto = new RecetteDto();
        recetteDto.setId(10L);
        PlanRepasDto planRepasDto = new PlanRepasDto();
        planRepasDto.setRecettes(new ArrayList<>());

        when(planRepasService.getPlanRepasById(planRepasId)).thenReturn(planRepasDto);
        when(recetteService.save(any(RecetteDto.class))).thenReturn(recetteDto);
        when(planRepasService.save(any(PlanRepasDto.class))).thenReturn(planRepasDto);

        ResponseEntity<RecetteDto> response = planRepasController.addRecetteToPlanRepas(planRepasId, recetteDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10L, response.getBody().getId());
        assertEquals(1, planRepasDto.getRecettes().size());
    }

    @Test
    public void testAddRecetteToPlanRepas_PlanNotFound() {
        Long planRepasId = 1L;
        RecetteDto recetteDto = new RecetteDto();
        when(planRepasService.getPlanRepasById(planRepasId)).thenReturn(null);

        ResponseEntity<RecetteDto> response = planRepasController.addRecetteToPlanRepas(planRepasId, recetteDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddRecetteToPlanRepas_NullRecetteDto() {
        Long planRepasId = 1L;
        PlanRepasDto planRepasDto = new PlanRepasDto();
        planRepasDto.setRecettes(new ArrayList<>());

        when(planRepasService.getPlanRepasById(planRepasId)).thenReturn(planRepasDto);

        ResponseEntity<RecetteDto> response = planRepasController.addRecetteToPlanRepas(planRepasId, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}