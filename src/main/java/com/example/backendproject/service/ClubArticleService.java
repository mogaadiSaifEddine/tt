package com.example.backendproject.service;

import com.example.backendproject.entities.ArticleClub;
import com.example.backendproject.repos.ArticleClubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClubArticleService {
    @Autowired
    private ArticleClubRepo clubRepo;


    public ArticleClub addArticleClub(ArticleClub club){
        return clubRepo.save(club);
    }
    public ArticleClub UpdateClubArticle(ArticleClub club){
        ArticleClub clubToUpdate  = clubRepo.findById(club.getFeed_id()).orElse(null);
        assert clubToUpdate != null;
        clubToUpdate.setPost(club.getPost());
        clubToUpdate.setDescription(club.getDescription());
        clubToUpdate.setImage(club.getImage());

        return clubRepo.save(club);
    }
    public Boolean DeleteClubArticle(Long id){
        clubRepo.deleteById(id);
        return true;
    }
    public List<ArticleClub> GetAllClubArticle(){
        return clubRepo.findAll();
    }
}
