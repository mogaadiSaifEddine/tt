package com.example.backendproject.restControllers;


import com.example.backendproject.entities.Block;
import com.example.backendproject.entities.Exercice_Block;
import com.example.backendproject.repos.ExercBlockRepository;
import com.example.backendproject.service.ExerciceBlockService;
import com.example.backendproject.service.ExerciceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exerciceBlock")
public class ExerciceBlockController {
    @Autowired
    ExerciceBlockService exerciceBlockService;

    @Autowired
    ExercBlockRepository exrciceBlockRepository;
    @PostMapping("/")
    public Exercice_Block Addblock(@RequestBody Exercice_Block block) {
//        System.out.println(block.getBlock().getBlock_id());
        System.out.println("block.getExercice().getExercice_id()");
//        block.setBlock(blockService.findById(block.getBlock().getBlock_id()));
        return exerciceBlockService.AddExercice(block);
    }
    @GetMapping("/")
    public List<Exercice_Block> getBlocks() {
        return exerciceBlockService.findAll();
    }
    @GetMapping("/id")
    public Exercice_Block getBlockByid(@PathVariable Long id ) {
        System.out.println("Hello from the other side ");
        return exrciceBlockRepository.findById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public Exercice_Block getBlocks( @PathVariable Long id , @RequestBody Exercice_Block exercice_block) {
        return exerciceBlockService.updateExercice(exercice_block);
    }
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id ) {
         exerciceBlockService.deleteExercice(id);
    }

}
