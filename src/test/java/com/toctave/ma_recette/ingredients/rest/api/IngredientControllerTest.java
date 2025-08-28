package com.toctave.ma_recette.ingredients.rest.api;

import com.toctave.ma_recette.ingredients.services.IngredientDto;
import com.toctave.ma_recette.ingredients.services.IngredientService;
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

public class IngredientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testGetAllIngredients() throws Exception {
        List<IngredientDto> ingredients = Arrays.asList(new IngredientDto(), new IngredientDto());
        when(ingredientService.findAll()).thenReturn(ingredients);

        mockMvc.perform(get("/api/ingredients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void testGetIngredientById() throws Exception {
        IngredientDto ingredientDto = new IngredientDto();
        when(ingredientService.findById(1L)).thenReturn(ingredientDto);

        mockMvc.perform(get("/api/ingredients/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testGetIngredientById_NotFound() throws Exception {
        when(ingredientService.findById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/ingredients/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateIngredient() throws Exception {
        IngredientDto ingredientDto = new IngredientDto();
        when(ingredientService.save(any(IngredientDto.class))).thenReturn(ingredientDto);

        mockMvc.perform(post("/api/ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateIngredient() throws Exception {
        IngredientDto ingredientDto = new IngredientDto();
        when(ingredientService.findById(1L)).thenReturn(ingredientDto);
        when(ingredientService.save(any(IngredientDto.class))).thenReturn(ingredientDto);

        mockMvc.perform(put("/api/ingredients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateIngredient_NotFound() throws Exception {
        when(ingredientService.findById(1L)).thenReturn(null);

        mockMvc.perform(put("/api/ingredients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        IngredientDto ingredientDto = new IngredientDto();
        when(ingredientService.findById(1L)).thenReturn(ingredientDto);

        mockMvc.perform(delete("/api/ingredients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteIngredient_NotFound() throws Exception {
        when(ingredientService.findById(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/ingredients/1"))
                .andExpect(status().isNotFound());
    }
}
