package com.example.backendproject.restControllers;


import com.example.backendproject.entities.Club;
import com.example.backendproject.repos.ClubRepo;
import com.example.backendproject.service.ClubService;
import com.example.backendproject.service.files.FileStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;
    @Autowired
    private ClubRepo clubRepo;

@Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/")
    public Club AddClub(@RequestBody Club club)  {
//





//        user.setLogo(url);
//        clubService.AddClub(user);
        return clubService.AddClub(club);
    }

    @PostMapping("/upload")
     public Boolean uploadFileClub(@RequestParam MultipartFile file , @RequestParam String idClub ) throws JsonProcessingException {
        Long id  = Long.valueOf(idClub) ;
        Club club = clubRepo.findById(id).orElse(null) ;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date(System.currentTimeMillis());
        String url  = formatter.format(date) +file.getOriginalFilename()  ;
        fileStorageService.saveClub(file, url);
        assert club != null;
        club.setLogo(url);
        clubService.UpdateClub(club);
        return true ;
    }
    @PutMapping("/")
    public Club UpdateClub( @RequestBody   Club club){
        return clubService.UpdateClub(club);
    }
    @DeleteMapping("/{id}")
    public void DeleteClub( @PathVariable Long id){
        clubService.DeleteClub(id);
    }
    @GetMapping("/")
    public List<Club> GetAllClub(){
        return clubService.GetAllClub();
    }



}
