package com.example.backendproject.repos;

import com.example.backendproject.entities.BlockFile;
import com.example.backendproject.entities.CoursePR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockFileRepo extends JpaRepository<BlockFile,Long> {
}
