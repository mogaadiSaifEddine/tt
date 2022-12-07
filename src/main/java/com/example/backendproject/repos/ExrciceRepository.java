package com.example.backendproject.repos;

import com.example.backendproject.entities.ExerciceSerie;
import com.example.backendproject.entities.Exrcise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExrciceRepository extends JpaRepository<Exrcise,Long> {

 List<Exrcise> findByexerciceSerie(ExerciceSerie serie);
}
