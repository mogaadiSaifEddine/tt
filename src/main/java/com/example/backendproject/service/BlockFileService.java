package com.example.backendproject.service;


import com.example.backendproject.entities.*;
import com.example.backendproject.repos.*;
import com.example.backendproject.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLConnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

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
                String fileName = Objects.requireNonNull(file.getOriginalFilename()).substring(0 , file.getOriginalFilename().length()-1);
                System.out.println(file);
                String url = formatter.format(date) + fileName;
                fileStorageService.addBlockFiles(file, url);
                if (chapter != null)
                    if (file.isEmpty()) {
                        System.out.println("Empty File");
                    } else {
                        byte[] bytes = file.getBytes();
                        System.out.println("File successfully uploaded to local storage : " + file.getOriginalFilename());
                    }
                System.out.println(file.getContentType());
                System.out.println(file.getName());
                String mimeType = URLConnection.guessContentTypeFromName(file.getOriginalFilename());
                BlockFile f = new BlockFile();
                System.out.println(mimeType);
                f.setName(file.getName());
                f.setUrl(url);
                f.setBlock(chapter);
                f.setType(file.getContentType());
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