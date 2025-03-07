package com.toctave.ma_recette.utilisateurs.repositories;

import com.toctave.ma_recette.utilisateurs.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
