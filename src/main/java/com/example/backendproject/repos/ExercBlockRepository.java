package com.example.backendproject.repos;

import com.example.backendproject.entities.Exercice_Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercBlockRepository extends JpaRepository<Exercice_Block, Long> {


//    findyByUserExercice(Long user_id , Long exercice_id);
}
