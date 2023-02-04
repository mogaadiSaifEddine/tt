package com.example.backendproject.service;


import com.example.backendproject.entities.Exercice_Block;
import com.example.backendproject.entities.Exrcise;
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



    public Exrcise AddExercice(Exrcise e) {
        return  exrciceRepository.save(e);
    }

    public Exrcise updateExercice(Exrcise e , Long id ) {
        Exrcise exrcice = exrciceRepository.findById(id).orElse(null);

        assert exrcice != null;
        exrcice.setName(e.getName());
        if (!exrcice.getBlocks().isEmpty()){
            for (Exercice_Block exercice_block:exrcice.getBlocks()
            ) {

                exercice_block.setExercice(null);
                exercice_block.setExercice(null);
                exercBlockRepository.save(exercice_block);
            }
        }

        if (!e.getBlocks().isEmpty()) {
            for (Exercice_Block exercice_block : e.getBlocks()
            ) {
                exercice_block.setExercice(exrcice);
                exercBlockRepository.save(exercice_block);
            }
        }
        exrcice.setRTL(e.isRTL());
        exrcice.setBlocks(e.getBlocks());
        exrcice.setDifficulty(e.getDifficulty());
        exrcice.setType(e.getType());
        exrcice.setQuestion(e.getQuestion());


        return exrciceRepository.save(exrcice);
    }

    public void deleteExercice(Long id) { exrciceRepository.deleteById(id);}

    public List<Exrcise> findAll() {
        return exrciceRepository.findAll();
    }


}