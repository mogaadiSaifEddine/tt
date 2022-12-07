package com.example.backendproject.restControllers;

import com.example.backendproject.entities.Catre_Conceptuelle;
import com.example.backendproject.repos.Carte_Conceptuelle_Repo;
import com.example.backendproject.service.Carte_Conceptuelle_Service;
import com.example.backendproject.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@CrossOrigin(origins = "*")
public class Carte_Conceptuelle_Controller {
    @Autowired
    Carte_Conceptuelle_Service fs;


    @Autowired
    Carte_Conceptuelle_Repo carte_conceptuelle_repo;



    @Autowired
    FileStorageService fileStorageService;
    @PostMapping("/chapter/cart/{idChapter}")
    @ResponseBody
    public Catre_Conceptuelle addFile(@PathVariable("idChapter") Long idChapter, @RequestParam("file") MultipartFile file) {
    System.out.println(file);
        return fs.addFile(file, idChapter);
    }

    @GetMapping("/chapter/cart/{idFile}")
    @ResponseBody
    public ResponseEntity<Resource> getCarte(@PathVariable Long idFile){

        Catre_Conceptuelle fileDB = carte_conceptuelle_repo.findById(idFile).orElse(null);
        assert fileDB != null;
        String  filename = fileDB.getUrl();
         Resource file = fileStorageService.loadCart(filename);
         return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
