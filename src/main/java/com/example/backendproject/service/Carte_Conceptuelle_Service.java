package com.example.backendproject.service;

import com.example.backendproject.entities.Catre_Conceptuelle;
import com.example.backendproject.entities.Chapter;
import com.example.backendproject.entities.Exrcise;
import com.example.backendproject.repos.Carte_Conceptuelle_Repo;
import com.example.backendproject.repos.ChapterRepo;
import com.example.backendproject.repos.ExrciceRepository;
import com.example.backendproject.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class Carte_Conceptuelle_Service {
    @Autowired
    Carte_Conceptuelle_Repo fr;

    @Autowired
    ChapterRepo er;

    @Autowired
    ServletContext context;


    @Autowired
    FileStorageService fileStorageService;
    @Value("${ConceptuelCardFolder}")
    private String conceptuelCardFolder;
    @Value("${CoureseFolder}")
    private String coureseFolder;


    @Value("${clubFolder}")
    private String clubFolder;

    @Value("${ArticleClubFolder}")
    private String articleClubFolder;
    public Catre_Conceptuelle addFile(MultipartFile file, Long idExercice) {



        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(file);
        String url  = formatter.format(date) +file.getOriginalFilename()  ;
//        File uploadRootDir = new File(conceptuelCardFolder);
        fileStorageService.addCardConceptuelFile(file, url);
        // Create directory if it not exists.
//        if (!uploadRootDir.exists()) {
//            uploadRootDir.mkdirs();
//        }





        Chapter chapter=er.findById(idExercice).orElse(null);
        if(chapter==null) return null;

        try{

            if (file.isEmpty()) {
                System.out.println("Empty File");
            } else {
                byte[] bytes = file.getBytes();

//                System.out.println(Files.write(path, bytes));

                System.out.println("File successfully uploaded to local storage : " + file.getOriginalFilename());}


            Catre_Conceptuelle f = new Catre_Conceptuelle();
            f.setName(file.getOriginalFilename());
            f.setUrl(url);
//            f.setChapter(chapter);
    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
            System.out.println(f.getUrl());
            Catre_Conceptuelle cp = fr.save(f);
            chapter.setCatre_conceptuelle(cp);
            er.save(chapter);
            return cp;
        }catch (Exception e){
            System.out.println("Error Uploading file"+e.getMessage());
            return null;

        }

    }





    public Catre_Conceptuelle getFileById(long id) {
        return fr.findById(id).orElse(null);
    }
}
