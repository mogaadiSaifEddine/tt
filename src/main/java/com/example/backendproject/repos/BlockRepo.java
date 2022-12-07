package com.example.backendproject.repos;

import com.example.backendproject.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepo extends JpaRepository<Block,Long> {
}
