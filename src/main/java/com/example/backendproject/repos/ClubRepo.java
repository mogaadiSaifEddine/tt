package com.example.backendproject.repos;

import com.example.backendproject.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepo extends JpaRepository<Club, Long> {
}

