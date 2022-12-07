package com.example.backendproject.service;


import com.example.backendproject.entities.Chapter;
import com.example.backendproject.entities.Course;
import com.example.backendproject.entities.ExerciceFile;
import com.example.backendproject.entities.Exrcise;
import com.example.backendproject.repos.ChapterRepo;
import com.example.backendproject.repos.CourserRepo;
import com.example.backendproject.repos.ExerciceFileRepo;
import com.example.backendproject.repos.ExrciceRepository;
import com.example.backendproject.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
public class ExerciceFileService {
   @Autowired
   ExerciceFileRepo fr;

    @Autowired
    FileStorageService fileStorageService;


    @Autowired
    ExrciceRepository er;
    @Value("${ConceptuelCardFolder}")
    private String conceptuelCardFolder;
    @Value("${CoureseFolder}")
    private String coureseFolder;


    @Value("${clubFolder}")
    private String clubFolder;

    @Value("${ArticleClubFolder}")
    private String articleClubFolder;
    public ExerciceFile AddCourse(MultipartFile file, Long idExercice){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(file);
        String url  = formatter.format(date) +file.getOriginalFilename()  ;
//        File uploadRootDir = new File(conceptuelCardFolder);
        fileStorageService.addExerciceFile(file, url);
        // Create directory if it not exists.
//        if (!uploadRootDir.exists()) {
//            uploadRootDir.mkdirs();
//        }





        Exrcise chapter=er.findById(idExercice).orElse(null);
        if(chapter==null) return null;

        try{

            if (file.isEmpty()) {
                System.out.println("Empty File");
            } else {
                byte[] bytes = file.getBytes();

//                System.out.println(Files.write(path, bytes));

                System.out.println("File successfully uploaded to local storage : " + file.getOriginalFilename());}


            ExerciceFile f = new ExerciceFile();
            f.setName(file.getOriginalFilename());
            f.setUrl(url);
//            f.setChapter(chapter);

            System.out.println(f.getUrl());
            ExerciceFile cp = fr.save(f);
            chapter.setExerciceFile(cp);
            er.save(chapter);
            return cp;
        }catch (Exception e){
            System.out.println("Error Uploading file"+e.getMessage());
            return null;

        }
//        return courserRepo.save(course);
    }
//        public Course UpdateCourse(Course course){
//        return courserRepo.save(course);
//    }
//    public void DeleteCourse(Long id){
//        courserRepo.deleteById(id);
//    }
//    public List<Course> GetAllCourse(){
//        return courserRepo.findAll();
//    }


}
