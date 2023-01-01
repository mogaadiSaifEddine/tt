package com.example.backendproject.restControllers;

import com.example.backendproject.entities.BlockFile;
import com.example.backendproject.repos.BlockFileRepo;
import com.example.backendproject.service.BlockFileService;
import com.example.backendproject.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/block_exercice/file")

public class BlockfileController {
    @Autowired
    BlockFileService courseService;

    @Autowired
    BlockFileRepo courserRepo;


    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/{idBlock}")
    public Boolean AddCourse(@PathVariable("idBlock") Long idBlock, @RequestParam("file") List< MultipartFile> file) {
        return courseService.AddCourse(file, idBlock);
    }

    @GetMapping("/{idFileblock}")
    @ResponseBody
    public ResponseEntity<Resource> getCarte(@PathVariable Long idFileblock) {

        BlockFile fileDB = courserRepo.findById(idFileblock).orElse(null);
        assert fileDB != null;
        String filename = fileDB.getUrl();
        Resource file = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


}
