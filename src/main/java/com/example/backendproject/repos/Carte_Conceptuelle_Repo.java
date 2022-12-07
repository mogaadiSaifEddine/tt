package com.example.backendproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backendproject.entities.Catre_Conceptuelle;

@Repository
public interface Carte_Conceptuelle_Repo extends JpaRepository<Catre_Conceptuelle,Long> {
}
