package com.toctave.ma_recette.utilisateurs.services;

import com.toctave.ma_recette.utilisateurs.Utilisateur;
import com.toctave.ma_recette.utilisateurs.repositories.UtilisateurRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<UtilisateurDto> findAll() {
        return UtilisateurMapper.INSTANCE.toUtilisateurDto(utilisateurRepository.findAll());
    }

    public UtilisateurDto findById(Long id) {
        Optional<Utilisateur> optional = utilisateurRepository.findById(id);
        return optional.map(UtilisateurMapper.INSTANCE::toUtilisateurDto).orElse(null);
    }

    public UtilisateurDto save(@NonNull UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = UtilisateurMapper.INSTANCE.toUtilisateur(utilisateurDto);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return UtilisateurMapper.INSTANCE.toUtilisateurDto(savedUtilisateur);
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
