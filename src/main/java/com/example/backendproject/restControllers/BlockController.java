package com.example.backendproject.restControllers;

import com.example.backendproject.entities.Block;
import com.example.backendproject.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/block")

public class BlockController {
    @Autowired
    BlockService blockService;
    @PostMapping("/")
    public Block Addblock(@RequestBody Block block) {
        return blockService.Addblock(block);
    }

    @PutMapping("/")
    public Block UpdateBlock(@RequestBody Block block) {
        return blockService.UpdateBlock(block);
    }

    @DeleteMapping("/{id}")
    public void DeleteBlock(@PathVariable("id") Long id) {
        blockService.DeleteBlock(id);
    }

    @GetMapping("/")
    public List<Block> GetAllBlock() {
        return blockService.GetAllBlock();
    }







}
