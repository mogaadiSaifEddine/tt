package com.example.backendproject.repos;

import com.example.backendproject.entities.Parent;
import com.example.backendproject.entities.Student;
import com.example.backendproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends UserRepository {


    List<Student> findByParent(Parent parent);
    User findByUsername(String username);
}
