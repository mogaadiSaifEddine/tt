package com.example.backendproject.service;


import com.example.backendproject.entities.Chapter;
import com.example.backendproject.entities.ExerciceSerie;
import com.example.backendproject.entities.SeriesType;
import com.example.backendproject.repos.ChapterRepo;
import com.example.backendproject.repos.ExerciceSerieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {
   @Autowired
    ChapterRepo chapterRepo;
   @Autowired
    ExerciceSerieRepo exerciceSerieRepo ;
    public Chapter AddChapter(Chapter chapter){
        Chapter chp = chapterRepo.save(chapter);
        for (SeriesType type: SeriesType.values()
             ) {
            ExerciceSerie exerciceSerie = new ExerciceSerie();
            exerciceSerie.setSeriesType(type);
            exerciceSerie.setChapter(chp);
            exerciceSerieRepo.save(exerciceSerie);


            
        }


        return chp;
    }
//    public Chapter UpdateBlock(Chapter chapter){
//
//
//        return chapterRepo.save(chapter1);
//    }
    public void DeleteChapter(Long id){
        Chapter chapter  =chapterRepo.findById(id).orElse(null) ;
        assert chapter != null;
//        Chapter parent  = chapterRepo.findChapterByChildren(chapter);
//        System.out.println(parent.getChildren().size());
//        parent.setChildren(parent   .getChildren().stream().filter(chapter1 -> chapter!=chapter1).collect(Collectors.toList()));
////       chapter .getChildren().stream().map((chapter1 -> {
////            chapterRepo.delete(chapter1);
////            return  true;
////        })).collect(Collectors.toList())
////        chapter.setChildren(null);
//        parent.setChildren(null);
//        chapterRepo.save(chapter);
//        chapter.setExerciceSeries(null);
        chapterRepo.deleteById(id);
    }
    public List<Chapter> GetAllChapter(){
        return chapterRepo.findAll();
    }
    public Chapter GetOneChapter(Long id ){
        return chapterRepo.findById(id).orElse(null);
    }


}
