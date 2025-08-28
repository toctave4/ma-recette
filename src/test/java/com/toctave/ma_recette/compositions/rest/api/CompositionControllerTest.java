package com.toctave.ma_recette.compositions.rest.api;

import com.toctave.ma_recette.compositions.services.CompositionDto;
import com.toctave.ma_recette.compositions.services.CompositionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CompositionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompositionService compositionService;

    @InjectMocks
    private CompositionController compositionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(compositionController).build();
    }

    @Test
    public void testGetAllCompositions() throws Exception {
        List<CompositionDto> compositions = Arrays.asList(new CompositionDto(), new CompositionDto());
        when(compositionService.findAll()).thenReturn(compositions);

        mockMvc.perform(get("/api/compositions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void testGetCompositionsByRecetteId() throws Exception {
        List<CompositionDto> compositions = Arrays.asList(new CompositionDto(), new CompositionDto());
        when(compositionService.findAllByRecetteId(1L)).thenReturn(compositions);

        mockMvc.perform(get("/api/compositions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void testGetCompositionById() throws Exception {
        CompositionDto compositionDto = new CompositionDto();
        when(compositionService.getCompositionById(1L, 1L)).thenReturn(compositionDto);

        mockMvc.perform(get("/api/compositions/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testCreateComposition() throws Exception {
        CompositionDto compositionDto = new CompositionDto();
        when(compositionService.save(any(CompositionDto.class))).thenReturn(compositionDto);

        mockMvc.perform(post("/api/compositions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
    }
}
