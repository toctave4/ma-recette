package com.toctave.ma_recette.recettes.services;

import com.toctave.ma_recette.recettes.Recette;
import com.toctave.ma_recette.recettes.repositories.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetteService {

    private final RecetteRepository recetteRepository;

    @Autowired
    public RecetteService(RecetteRepository recetteRepository) {
        this.recetteRepository = recetteRepository;
    }

    public List<RecetteDto> findAll() {
        List<Recette> recettes = recetteRepository.findAll();
        return RecetteMapper.INSTANCE.toRecetteDto(recettes);
    }

    public RecetteDto findById(Long id) {
        Optional<Recette> recetteOptional = recetteRepository.findById(id);
        return recetteOptional.map(RecetteMapper.INSTANCE::toRecetteDto).orElse(null);
    }

    public RecetteDto save(RecetteDto recetteDto) {
        Recette recette = RecetteMapper.INSTANCE.toRecette(recetteDto);
        Recette savedRecette = recetteRepository.save(recette);
        return RecetteMapper.INSTANCE.toRecetteDto(savedRecette);
    }

    public void delete(Long id) {
        recetteRepository.deleteById(id);
    }
}
