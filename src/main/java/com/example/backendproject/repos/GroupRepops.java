package com.example.backendproject.repos;

import com.example.backendproject.entities.Group;
import com.example.backendproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepops extends JpaRepository<Group  , Long> {
    Group findGroupByUsers(User userId);

}
