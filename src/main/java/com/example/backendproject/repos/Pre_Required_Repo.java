package com.example.backendproject.repos;

import com.example.backendproject.entities.Pre_Required_Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface Pre_Required_Repo extends JpaRepository<Pre_Required_Chapter,Long> {
}
