package com.toctave.ma_recette.utilisateurs.rest.api;

import com.toctave.ma_recette.utilisateurs.services.UtilisateurDto;
import com.toctave.ma_recette.utilisateurs.services.UtilisateurService;
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

class UtilisateurControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();
    }

    @Test
    public void testGetAllUtilisateurs() throws Exception {
        List<UtilisateurDto> utilisateurList = Arrays.asList(new UtilisateurDto(), new UtilisateurDto());
        when(utilisateurService.findAll()).thenReturn(utilisateurList);

        mockMvc.perform(get("/api/utilisateurs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void testGetUtilisateurById() throws Exception {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.findById(1L)).thenReturn(utilisateurDto);

        mockMvc.perform(get("/api/utilisateurs/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testGetUtilisateurById_NotFound() throws Exception {
        when(utilisateurService.findById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/utilisateurs/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUtilisateur() throws Exception {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.save(any(UtilisateurDto.class))).thenReturn(utilisateurDto);

        mockMvc.perform(post("/api/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateUtilisateur() throws Exception {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.findById(1L)).thenReturn(utilisateurDto);
        when(utilisateurService.save(any(UtilisateurDto.class))).thenReturn(utilisateurDto);

        mockMvc.perform(put("/api/utilisateurs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateUtilisateur_NotFound() throws Exception {
        when(utilisateurService.findById(1L)).thenReturn(null);

        mockMvc.perform(put("/api/utilisateurs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUtilisateur() throws Exception {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.findById(1L)).thenReturn(utilisateurDto);

        mockMvc.perform(delete("/api/utilisateurs/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteUtilisateur_NotFound() throws Exception {
        when(utilisateurService.findById(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/utilisateurs/1"))
                .andExpect(status().isNotFound());
    }
}
