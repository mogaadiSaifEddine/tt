package com.example.backendproject.repos;

import com.example.backendproject.entities.Exercice_Block;
import com.example.backendproject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercBlockRepository extends JpaRepository<Exercice_Block, Long> {
}
