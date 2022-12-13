package com.example.backendproject.restControllers;

import  com.example.backendproject.entities.*;
import com.example.backendproject.payload.response.ChapterResMenu;
import com.example.backendproject.repos.*;
import com.example.backendproject.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Chapter")

public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @Autowired
    ExerciceSerieRepo   exerciceSerieRepo;
    @Autowired
    ChapterRepo chapterRepo;
    @Autowired
    GroupRepops groupRepops;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ExrciceRepository exrciseRepo;

    @Autowired
    ExercBlockRepository exercBlockRepository;

    // add a chapter by grp
    @PostMapping("/{grpId}/{userId}/{chapId}")
    public Chapter AddChapter(@RequestBody Chapter chapter , @PathVariable Long  grpId , @PathVariable Long userId ,@PathVariable Long chapId) {
        System.out.println(chapter);
        User teacher  = userRepository.findById(userId).orElse(null) ;
        Group grp = groupRepops.findById(grpId).orElse(null)  ;
        System.out.println(grp);
        chapter.setGroup(grp);

        chapter.setTeacher(teacher);

        Chapter ch  = chapterService.AddChapter(chapter);
        if (chapId == 0) return ch;

        Chapter parent  = chapterRepo.findById(chapId).orElse(null) ;System.out.println(parent);
        assert parent != null;
        parent.getChildren().add(ch);
        chapter.setParent(parent);
        chapterRepo.save(parent) ;

        return ch;
    }
    @PutMapping("/{chapid}/{grpId}/")
    public Chapter UpdateChapter(@RequestBody Chapter chapter ,  @PathVariable Long  grpId,@PathVariable Long  chapid ) {
        Chapter chapter1 = chapterRepo.findById(chapid).orElse(null) ;
        assert chapter1 != null;

        Group grp = groupRepops.findById(grpId).orElse(null)  ;
        chapter.setGroup(grp);
        chapter1.setName(chapter.getName());
        chapter1.setUrl(chapter.getUrl());

        return chapterRepo.save(chapter1 );
    }
    @DeleteMapping("/{id}")
    public void DeleteChapter(@PathVariable("id") Long id) {
        chapterService.DeleteChapter(id);
    }
    @GetMapping("/")
    public List<Chapter> GetAllChapter() {
        return chapterService.GetAllChapter();
    }
    @GetMapping("/menu_elements")
    public List<Chapter> GetMenuchapters( ) {
//        List<Chapter> chapterList = chapterService.GetAllChapter();
//        List<Chapter> chapterListMenu = new ArrayList<>();
//        for (Chapter chapter : chapterList) {
//            ChapterResMenu chapterResMenu = new ChapterResMenu();
//            while (chapter.getSousChapitres() != null) {
//                for (Chapter sousChapitre : chapter.getSousChapitres()) {
//                    chapter = sousChapitre;
//                }
//            }
//            chapterResMenu.setId(chapter.getChapter_id());
//            chapterResMenu.setName(chapter.getName());
//            chapterResMenu.setParentId(chapter.getChapterParent().getChapter_id());
//            chapterResMenu.setSousChapitres(chapterService.GetMenuchapters(chapter.getId()));
//            chapterListMenu.add(chapterResMenu);
//        }
        return new ArrayList<Chapter>() ;
    }
    @GetMapping("/{id}")
    public Chapter GetOneChapter(@PathVariable Long id ) {
        return chapterService.GetOneChapter(id);
    }

    @GetMapping("/niveau/{niveau}")
    public  List <Chapter> getVhapterByNiveau(@PathVariable Long niveau ) {
        Group group  = groupRepops.findById(niveau).orElse(null);
        return chapterRepo.findByGroup(group);
    }
    @GetMapping("/teacher/{userId}")
    public  List <Chapter> getVhapterByTeacher(@PathVariable Long userId ) {
        User user  = userRepository.findById(userId).orElse(null);
        return chapterRepo.findByTeacher(user).stream().filter(chapter -> chapter.getChapterType() == ChapterType.CHAPTER) .collect(Collectors.toList());
    }
    @GetMapping("/matieres/")
    public   List <Chapter> getModules() {
        return chapterRepo.findAll().stream().filter(chapter -> chapter.getChapterType() == ChapterType.MATIERE) .collect(Collectors.toList());
    }


    @GetMapping("/matieres/{grpId}")
    public   List <Chapter> getModulesEleves(@PathVariable Long grpId) {
        Group group =groupRepops.findById(grpId).orElse(null);
        return chapterRepo.findByGroup(group).stream().filter(chapter -> chapter.getChapterType() == ChapterType.MATIERE) .collect(Collectors.toList());
    }
    @GetMapping("/chap_mat/{matId}")
    public   List <Chapter> getChapMat(@PathVariable Long matId) {
//        Group group =groupRepops.findById(grpId).orElse(null);

        Chapter parent  = chapterRepo.findById(matId).orElse(null);

        return chapterRepo.findByParent(parent);    }








}
