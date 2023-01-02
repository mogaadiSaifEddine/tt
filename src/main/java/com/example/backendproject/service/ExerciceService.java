package com.example.backendproject.service;


import com.example.backendproject.entities.Chapter;
import com.example.backendproject.entities.ExerciceSerie;
import com.example.backendproject.entities.Exercice_Block;
import com.example.backendproject.entities.Exrcise;
import com.example.backendproject.repos.ChapterRepo;
import com.example.backendproject.repos.ExercBlockRepository;
import com.example.backendproject.repos.ExrciceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceService {
    @Autowired
    ExrciceRepository exrciceRepository;
    @Autowired
    ExercBlockRepository exercBlockRepository;
    @Autowired
    private ChapterRepo chapterRepo;


    public Exrcise AddExercice(Exrcise e) {
        Chapter chapter = e.getExerciceSerie().getChapter();
        System.out.println(e.getBlocks().get(0).toString());
        chapter.setIsValid(false);
        chapterRepo.save(chapter);
        exrciceRepository.save(e);
        return e;

    }

    public Exrcise updateExercice(Exrcise updatedExercise, Long id) {
        Exrcise existingExercise = exrciceRepository.findById(id).orElse(null);
        if (existingExercise == null) {
            return null;
        }
        Chapter chapter = existingExercise.getExerciceSerie().getChapter();
        chapter.setIsValid(updatedExercise.isValid());
        if (updatedExercise.isValid()) updatedExercise.setAdvisorComment(null);
        if (updatedExercise.isValid()) {
            checkChapterValid(chapter);
        }
        chapterRepo.save(chapter);
        updatedExercise.setAdvisorComment(updatedExercise.getAdvisorComment());
        existingExercise.setName(updatedExercise.getName());
        existingExercise.setValid(updatedExercise.isValid());
        existingExercise.setRTL(updatedExercise.isRTL());
        existingExercise.setBlocks(updatedExercise.getBlocks());
        existingExercise.setDifficulty(updatedExercise.getDifficulty());
        existingExercise.setType(updatedExercise.getType());
        existingExercise.setQuestion(updatedExercise.getQuestion());

        if (!existingExercise.getBlocks().isEmpty()) {
            for (Exercice_Block exerciceBlock : existingExercise.getBlocks()) {
                exerciceBlock.setExercice(null);
                exercBlockRepository.save(exerciceBlock);
            }
        }

        if (!updatedExercise.getBlocks().isEmpty()) {
            for (Exercice_Block exerciceBlock : updatedExercise.getBlocks()) {
                exerciceBlock.setExercice(existingExercise);
                exercBlockRepository.save(exerciceBlock);
            }
        }

        return exrciceRepository.save(existingExercise);
    }

    private static void checkChapterValid(Chapter chapter) {
        boolean isOneExerciseInvalid = false;
        List<ExerciceSerie> exerciceSeries = chapter.getExerciceSeries();
        for (ExerciceSerie exerciceSerie : exerciceSeries) {
            for (Exrcise exercice : exerciceSerie.getExercices()) {
                if (!exercice.isValid()) {
                    isOneExerciseInvalid = true;
                    break;
                }
            }
        }
        chapter.setIsValid(!isOneExerciseInvalid);
    }


    public void deleteExercice(Long id) {

        Exrcise exrcise = exrciceRepository.findById(id).orElse(null);
        assert exrcise != null;
        Chapter chapter = chapterRepo.findById(exrcise.getExerciceSerie().getChapter().getChapter_id()).orElse(null);

       assert chapter!=null;
        checkChapterValid(chapter);
        chapterRepo.save(chapter);

        exrciceRepository.deleteById(id);}








    public List<Exrcise> findAll() {
        return exrciceRepository.findAll();
    }


}