package com.example.backendproject.restControllers;


import com.example.backendproject.entities.ArticleClub;
import com.example.backendproject.repos.ArticleClubRepo;
import com.example.backendproject.service.ClubArticleService;
import com.example.backendproject.service.files.FileStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/clubarticle")
@CrossOrigin(origins = "*")

public class ClubArticle {
    @Autowired
    private ClubArticleService clubArticleService;
    @Autowired
    private ArticleClubRepo clubArticleRepo;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/")
    public ArticleClub AddClubArticle(@RequestBody ArticleClub articleClub)  {
    System.out.println(articleClub.getClub().getId());
        return  clubArticleService.addArticleClub(articleClub);

    }
    @PostMapping("/upload")
    public ArticleClub AddClubArticleFile(@RequestParam MultipartFile file , @RequestParam String idClubArticle ) throws JsonProcessingException {
    ArticleClub articleClub = clubArticleRepo.findById(Long.valueOf(idClubArticle)).orElse(null);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
    Date date = new Date(System.currentTimeMillis());
    String url  = formatter.format(date) +file.getOriginalFilename()  ;
        fileStorageService.saveClubArticle(file, idClubArticle);
        assert articleClub != null;
        articleClub.setImage(url);
        return clubArticleRepo.save(articleClub);




    }



    @GetMapping("/")
    public List<ArticleClub> getAllClubArticle() {
        return clubArticleService.GetAllClubArticle();
    }

    @PutMapping("/")
    public ArticleClub UpdateClubArticle(@RequestBody ArticleClub articleClub) {
        return clubArticleService.UpdateClubArticle(articleClub);
    }
    @DeleteMapping("/{id}")
    public  Boolean DeleteArticleClub(@PathVariable Long id ){
   return clubArticleService.DeleteClubArticle(id);
    }




}
