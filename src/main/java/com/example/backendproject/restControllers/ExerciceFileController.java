package com.example.backendproject.restControllers;

import com.example.backendproject.entities.ExerciceFile;
import com.example.backendproject.repos.ExerciceFileRepo;
import com.example.backendproject.service.ExerciceFileService;
import com.example.backendproject.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exercice/file")

public class ExerciceFileController {
    @Autowired
    ExerciceFileService courseService;

    @Autowired
    ExerciceFileRepo courserRepo;


    @Autowired
    FileStorageService fileStorageService;
    @PostMapping("/{idExercice}")
    public ExerciceFile AddCourse(@PathVariable("idExercice") Long idExercice, @RequestParam("file") MultipartFile file) {
        return courseService.AddCourse(file, idExercice);
    }

@GetMapping("/{idFile}")
@ResponseBody
public ResponseEntity<Resource> getCarte(@PathVariable Long idFile){

    ExerciceFile fileDB = courserRepo.findById(idFile).orElse(null);
    assert fileDB != null;
    String  filename = fileDB.getUrl();
    Resource file = fileStorageService.loadExerciceFile(filename);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
}






}
