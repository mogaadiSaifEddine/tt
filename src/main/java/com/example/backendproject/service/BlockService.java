package com.example.backendproject.service;


import com.example.backendproject.entities.Block;
import com.example.backendproject.repos.BlockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {
    @Autowired
    BlockRepo blockRepo;
    public Block Addblock(Block block){
        return blockRepo.save(block);
    }
    public Block UpdateBlock(Block block){
        return blockRepo.save(block);
    }
    public void DeleteBlock(Long id){
        blockRepo.deleteById(id);
    }
    public List<Block> GetAllBlock(){
        return blockRepo.findAll();
    }




}
