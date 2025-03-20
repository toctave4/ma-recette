package com.toctave.ma_recette.utilisateurs.rest.api;

import com.toctave.ma_recette.utilisateurs.services.UtilisateurDto;
import com.toctave.ma_recette.utilisateurs.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable Long id) {
        UtilisateurDto utilisateur = utilisateurService.findById(id);
        return utilisateur != null ? ResponseEntity.ok(utilisateur) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public UtilisateurDto createUtilisateur(@RequestBody UtilisateurDto utilisateur) {
        return utilisateurService.save(utilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDto utlisateurDtoDetails) {
        UtilisateurDto updatedUtilisateur = utilisateurService.findById(id);
        if (updatedUtilisateur != null) {
            updatedUtilisateur.setNom(utlisateurDtoDetails.getNom());
            updatedUtilisateur.setPrenom(utlisateurDtoDetails.getPrenom());
            updatedUtilisateur.setEmail(utlisateurDtoDetails.getEmail());
            updatedUtilisateur.setMotDePasse(utlisateurDtoDetails.getMotDePasse());
            updatedUtilisateur.setPreferencesAlimentaires(utlisateurDtoDetails.getPreferencesAlimentaires());
            updatedUtilisateur.setRestrictionsDietetiques(utlisateurDtoDetails.getRestrictionsDietetiques());
            updatedUtilisateur.setObjectif(utlisateurDtoDetails.getObjectif());
            return ResponseEntity.ok(utilisateurService.save(updatedUtilisateur));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        if (utilisateurService.findById(id) != null) {
            utilisateurService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
