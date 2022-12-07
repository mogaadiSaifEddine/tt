package com.example.backendproject.service;


import com.example.backendproject.entities.Exercice_Block;
import com.example.backendproject.entities.Exrcise;
import com.example.backendproject.repos.ExercBlockRepository;
import com.example.backendproject.repos.ExrciceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceBlockService {
    @Autowired
    ExercBlockRepository exrciceBlockRepository;



    public Exercice_Block AddExercice(Exercice_Block e) {
        return  exrciceBlockRepository.save(e);
    }

    public Exercice_Block updateExercice(Exercice_Block e) {
        Exercice_Block exrcice = exrciceBlockRepository.findById(e.getExercice_Block_Id()).orElse(null);
        assert exrcice != null;
        exrcice.setLabel(e.getLabel());

        exrcice.setValue(e.getValue());
        exrcice.setIsAdmissable(e.getIsAdmissable());
        exrcice.setPlaceholder(e.getPlaceholder());
        exrcice.setExercice(e.getExercice());



        return exrciceBlockRepository.save(exrcice);
    }

    public void deleteExercice(Long id) { exrciceBlockRepository.deleteById(id);}

    public List<Exercice_Block> findAll() {
        return exrciceBlockRepository.findAll();
    }


}