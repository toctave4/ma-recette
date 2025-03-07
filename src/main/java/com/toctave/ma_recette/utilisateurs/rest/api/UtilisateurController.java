package com.toctave.ma_recette.utilisateurs.rest.api;

import com.toctave.ma_recette.utilisateurs.Utilisateur;
import com.toctave.ma_recette.utilisateurs.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurService.findById(id);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.save(utilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Optional<Utilisateur> utilisateur = utilisateurService.findById(id);
        if (utilisateur.isPresent()) {
            Utilisateur updatedUtilisateur = utilisateur.get();
            updatedUtilisateur.setNom(utilisateurDetails.getNom());
            updatedUtilisateur.setPrenom(utilisateurDetails.getPrenom());
            updatedUtilisateur.setEmail(utilisateurDetails.getEmail());
            updatedUtilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
            updatedUtilisateur.setPreferencesAlimentaires(utilisateurDetails.getPreferencesAlimentaires());
            updatedUtilisateur.setRestrictionsDietetiques(utilisateurDetails.getRestrictionsDietetiques());
            updatedUtilisateur.setObjectif(utilisateurDetails.getObjectif());
            return ResponseEntity.ok(utilisateurService.save(updatedUtilisateur));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        if (utilisateurService.findById(id).isPresent()) {
            utilisateurService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
