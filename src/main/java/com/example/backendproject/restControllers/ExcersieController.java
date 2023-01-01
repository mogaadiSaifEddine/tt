package com.example.backendproject.restControllers;

import com.example.backendproject.entities.ExerciceSerie;
import com.example.backendproject.entities.Exercice_Block;
import com.example.backendproject.entities.Exrcise;
import com.example.backendproject.repos.ExercBlockRepository;
import com.example.backendproject.repos.ExerciceSerieRepo;
import com.example.backendproject.repos.ExrciceRepository;
import com.example.backendproject.service.ExerciceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exercice")
public class ExcersieController {
    @Autowired
    ExerciceService exerciceService;
    @Autowired
    ExercBlockRepository exerciceBlockRepository;

    @Autowired
    ExerciceSerieRepo exerciceSerieRepo;


    @Autowired
    ExrciceRepository exrciceRepository;

    @PostMapping("/{idSerie}")
    public Exrcise save(@RequestBody Exrcise e, @PathVariable Long idSerie) {
        ExerciceSerie exerciceSerie = exerciceSerieRepo.findById(idSerie).orElse(null);
        assert exerciceSerie != null;
        exerciceSerie.getChapter().setIsValid(false);
        e.setExerciceSerie(exerciceSerie);
        Exrcise ex = exerciceService.AddExercice(e);
        System.out.println("user DEtails");
        List<Exercice_Block> exerciceBlockList = e.getBlocks();
        if (!exerciceBlockList.isEmpty()) {
            for (Exercice_Block exerBlock : exerciceBlockList
            ) {
                exerBlock.setExercice(ex);
                exerciceBlockRepository.save(exerBlock);

            }
        }

        return ex;


    }

    @PutMapping("/{id}")
    public Exrcise updateExercice(@RequestBody Exrcise e, @PathVariable Long id) {
        return exerciceService.updateExercice(e, id);
    }

    @DeleteMapping("/{id}")
    public void deleteExercice(@PathVariable("id") long id) {
        exerciceService.deleteExercice(id);
    }

    @GetMapping("/")
    public List<Exrcise> findAll() {
        return exerciceService.findAll();
    }

    @GetMapping("/serie/{serieId}")
    public List<Exrcise> findByExerciceSerie(@PathVariable Long serieId) {
        ExerciceSerie exerciceSerie = exerciceSerieRepo.findById(serieId).orElse(null);

        return exrciceRepository.findByexerciceSerie(exerciceSerie);
    }

    @GetMapping("/{Id}")
    public Exrcise getOneExercice(@PathVariable Long Id) {


        return exrciceRepository.findById(Id).orElse(null);
    }


}
