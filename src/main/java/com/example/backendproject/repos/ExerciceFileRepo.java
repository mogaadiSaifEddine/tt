package com.example.backendproject.repos;

import com.example.backendproject.entities.Course;
import com.example.backendproject.entities.ExerciceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciceFileRepo extends JpaRepository<ExerciceFile,Long> {
}
