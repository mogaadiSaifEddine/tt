package com.example.backendproject.restControllers;

import com.example.backendproject.entities.*;
import com.example.backendproject.repos.ChapterRepo;
import com.example.backendproject.repos.ExerciceSerieRepo;
import com.example.backendproject.repos.ExrciceRepository;
import com.example.backendproject.repos.UserRepository;
import com.example.backendproject.service.ExerciceBlockService;
import com.example.backendproject.service.ExerciceSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/serie_exercice")
@CrossOrigin(origins = "*")

public class ExerciceSerieController {
    @Autowired
    private ExerciceSerieRepo exerciceSerieRepo;
    @Autowired
    private ExrciceRepository exrciceRepository;
    @Autowired
    private ChapterRepo chapterRepo;
    @Autowired
    private ExerciceSerieService exerciceSerieService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    ExerciceBlockService exercice_blockService;

    @PostMapping("/")
    public ExerciceSerie AddSerie(@RequestBody ExerciceSerie serie){
        System.out
                .println(serie);
        ExerciceSerie ex = exerciceSerieService.AddSerie(serie);

        if (!ex.getExercices().isEmpty()) {
            for (Exrcise exercice : ex.getExercices()
            ) {
                exercice.setExerciceSerie(ex);
                Exrcise e = exrciceRepository.save(exercice);
                if (!exercice.getBlocks().isEmpty()) {
                    for (Exercice_Block exercice_block : exercice.getBlocks()
                    ) {
                        exercice_block.setExercice(e);
                        exercice_blockService.AddExercice(exercice_block);
                    }
                }

            }
        }
        return ex ;
    }
    @PutMapping("/")
    public ExerciceSerie UpdateSerie(@RequestBody ExerciceSerie serie){
        return exerciceSerieService.UpdateSerie(serie);
    }
    @DeleteMapping("/{id}")
    public Boolean DeleteSerie(@PathVariable Long id){
        exerciceSerieService.DeleteSerie(id);
        return true;
    }
    @GetMapping("/")
    public List<ExerciceSerie> getAllSerie(){
        return exerciceSerieService.GetAllSerie();
    }

    @GetMapping("/chapter/{chapter_id}")
    public List<ExerciceSerie> getSeriesExercisByChapter(@PathVariable Long chapter_id ){
        Chapter chapter = chapterRepo.findById(chapter_id).orElse(null);
        return exerciceSerieRepo.findByChapter(chapter);
    }


    @GetMapping("/{id}")
    public  ExerciceSerie getExerciceSerieByID (@PathVariable Long id)
    {
        return  this.exerciceSerieRepo.findById(id).orElse(null);
    }
}
