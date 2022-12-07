package com.example.backendproject.service;


import com.example.backendproject.entities.ExerciceSerie;
import com.example.backendproject.repos.ExerciceSerieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceSerieService {

    @Autowired
    private ExerciceSerieRepo exerciceSerieRepo;

    public ExerciceSerie AddSerie(ExerciceSerie serie){
        return exerciceSerieRepo.save(serie);
    }
    public ExerciceSerie UpdateSerie(ExerciceSerie serie){
        ExerciceSerie serieToUpdate = exerciceSerieRepo.findById(serie.getId()).orElse(null);
        assert serieToUpdate != null;
        serieToUpdate.setName(serie.getName());
        serieToUpdate.setDescription(serie.getDescription());
        serieToUpdate.setImage(serie.getImage());
        serieToUpdate.setExercices(serie.getExercices());
        return exerciceSerieRepo.save(serieToUpdate);
    }
    public Boolean DeleteSerie(Long id){
        exerciceSerieRepo.deleteById(id);
        return true;
    }
    public List<ExerciceSerie> GetAllSerie(){
        return exerciceSerieRepo.findAll();
    }
}


