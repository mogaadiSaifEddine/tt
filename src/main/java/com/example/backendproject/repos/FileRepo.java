package com.example.backendproject.repos;


import com.example.backendproject.entities.FileClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<FileClub, Long> {
}
