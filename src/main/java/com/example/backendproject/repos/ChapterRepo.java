package com.example.backendproject.repos;

import com.example.backendproject.entities.Chapter;
import com.example.backendproject.entities.Group;
import com.example.backendproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepo extends JpaRepository<Chapter,Long> {

//    List<Chapter> findByNiveau(Long niveau);

    List <Chapter> findByGroup (Group group);
    List <Chapter> findByTeacher (User user);
    List <Chapter> findByParentAndIsValid (Chapter  chapter , Boolean isValid);

    List <Chapter> findByIsValid(Boolean isValid) ;

    Chapter findChapterByChildren(Chapter children);
}
