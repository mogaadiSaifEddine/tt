package com.example.backendproject.repos;

import com.example.backendproject.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepo extends JpaRepository<User_answer,Long> {
    List<User_answer> findByStudent(Student user);

//    User_answer findByStudentAndExerciceSerie(User user, ExerciceSerie exercice);
    User_answer findByStudentAndExercice(User user, Exrcise exercice);
//    List<User_answer> findByExercice(Long exercice_id);

//    User_answer findByUserAndExercice(User user , Exrcise exrcise)
}
