package com.example.backendproject.service;


import com.example.backendproject.entities.Club;
import com.example.backendproject.repos.ClubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    @Autowired
    private ClubRepo clubRepo;


    public Club AddClub(Club club){
        return clubRepo.save(club);
    }
    public Club UpdateClub(Club club){
        Club clubToUpdate  = clubRepo.findById(club.getId()).orElse(null);
        assert clubToUpdate != null;
        clubToUpdate.setName(club.getName());
        clubToUpdate.setDescription(club.getDescription());
        clubToUpdate.setLogo(club.getLogo());

        return clubRepo.save(club);
    }
    public void DeleteClub(Long id){
        clubRepo.deleteById(id);
    }
    public List<Club> GetAllClub(){
        return clubRepo.findAll();
    }

}
