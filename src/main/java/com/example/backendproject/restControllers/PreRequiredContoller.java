package com.example.backendproject.restControllers;

import com.example.backendproject.entities.Chapter;
import com.example.backendproject.entities.Pre_Required_Chapter;
import com.example.backendproject.service.ChapterService;
import com.example.backendproject.service.Pre_Required_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/PreRequired")
public class PreRequiredContoller {
    @Autowired
    Pre_Required_Service chapterService;
    @PostMapping()
    public Pre_Required_Chapter AddChapter(@RequestBody Pre_Required_Chapter chapter) {
        return chapterService.Addprerequired(chapter);
    }
    @PutMapping("/")
    public Pre_Required_Chapter UpdateBlock(@RequestBody Pre_Required_Chapter chapter) {
        return chapterService.UpdatePreReaquired(chapter);
    }
    @DeleteMapping("/id")
    public void DeleteChapter(@PathVariable("id") Long id) {
        chapterService.Deleteperequired(id);
    }
    @GetMapping("/")
    public List<Pre_Required_Chapter> GetAllChapter() {
        return chapterService.GetAllPrerequired();
    }
}
