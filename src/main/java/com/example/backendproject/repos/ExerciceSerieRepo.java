package com.example.backendproject.repos;

import com.example.backendproject.entities.Chapter;
import com.example.backendproject.entities.ExerciceSerie;
import com.example.backendproject.entities.Exrcise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciceSerieRepo  extends JpaRepository<ExerciceSerie, Long> {
//   List<ExerciceSerie> findBy(Long entreprise_id);
//    findyByUserExercice(Long user_id , Long exercice_id);
//   List <ExerciceSerie>  findByUser(Long user_id);
//    @Query(value = "SELECT *  FROM user_answer WHERE user_answer.exercice.ex_id =:exId", nativeQuery = true)
//    List<ExerciceSerie>  getScoreByUserExercice(Long exId)    ;


    List<ExerciceSerie> findByChapter(Chapter chapter_chapter_id);
}
