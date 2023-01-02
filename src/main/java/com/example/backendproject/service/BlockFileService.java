package com.example.backendproject.service;


import com.example.backendproject.entities.*;
import com.example.backendproject.repos.*;
import com.example.backendproject.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class BlockFileService {
    @Autowired
    BlockFileRepo fr;

    @Autowired
    FileStorageService fileStorageService;


    @Autowired
    ExercBlockRepository er;
    @Value("${ConceptuelCardFolder}")
    private String conceptuelCardFolder;
    @Value("${CoureseFolder}")
    private String coureseFolder;


    @Value("${clubFolder}")
    private String clubFolder;

    @Value("${ArticleClubFolder}")
    private String articleClubFolder;

    public Boolean AddCourse(List<MultipartFile> multipartFiles, Long idExercice) {
        Exercice_Block chapter = er.findById(idExercice).orElse(null);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(multipartFiles.size());
        try {
            for (MultipartFile file : multipartFiles
            ) {
                System.out.println(file);
                String url = formatter.format(date) + file.getOriginalFilename();
                fileStorageService.addBlockFiles(file, url);
                if (chapter != null)
                    if (file.isEmpty()) {
                        System.out.println("Empty File");
                    } else {
                        byte[] bytes = file.getBytes();
                        System.out.println("File successfully uploaded to local storage : " + file.getOriginalFilename());
                    }
                BlockFile f = new BlockFile();
                f.setName(file.getOriginalFilename());
                f.setUrl(url);
                f.setBlock(chapter);
                System.out.println(f.getUrl());
                BlockFile cp = fr.save(f);
            }
            return true;

        } catch (Exception e) {
            System.out.println("Error Uploading file" + e.getMessage());
            return false;
        }

    }
}