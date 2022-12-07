package com.example.backendproject.repos;

import com.example.backendproject.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourserRepo extends JpaRepository<Course,Long> {
}
