package com.example.backendproject.service.files;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
public interface FileStorageService {
    public void init();
    public void saveClub(MultipartFile file , String url);
    public void saveClubArticle(MultipartFile file , String url);

    public void addCardConceptuelFile(MultipartFile file , String url);
    public void addExerciceFile(MultipartFile file , String url);
    public void addCourse(MultipartFile file , String url);
    public void addBlockFiles(MultipartFile file , String url);
    public void addCoursePR(MultipartFile file , String url);


    public Resource loadArticle(String filename);
    public Resource loadCart(String filename);
    public List<Resource> loadFileClub(List<String> filenamesList);
    public Resource loadCourse(String filename);
    public Resource loadCoursePR(String filename);
    public Resource load(String filename);
    public Resource loadExerciceFile(String filename);

    public void deleteAll();
    public Stream<Path> loadAll();

}
