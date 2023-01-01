package com.example.backendproject.service.files;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.example.backendproject.entities.ExerciceFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileStorageServiceImpl  implements FileStorageService    {


    @Value("${ConceptuelCardFolder}")
    private String conceptuelCardFolder;
    @Value("${CoureseFolder}")
    private String coureseFolder;
    @Value("${BlockFilesFolder}")
    private String BlockFilesFolder;

    @Value("${clubFolder}")
    private String clubFolder;

    @Value("${ArticleClubFolder}")
    private String articleClubFolder;
    @Value("${ExerciceFolder}")
    private String ExerciceFolder;
    @Value("${CoursePR}")
    private String CoursePRFolder;

    public FileStorageServiceImpl() {
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(Paths.get(clubFolder));

        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    @Override
    public void saveClub(MultipartFile file , String url) {
        try {
            Files.copy(file.getInputStream(),Paths.get(clubFolder).resolve(url));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }


    }

    @Override
    public void addExerciceFile(MultipartFile file , String url) {
        try {
            System.out.println(Paths.get(ExerciceFolder).resolve(url));
            Files.copy(file.getInputStream(),Paths.get(ExerciceFolder).resolve(url));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }


    } @Override
    public void addCoursePR(MultipartFile file , String url) {
        try {
            System.out.println(Paths.get(CoursePRFolder).resolve(url));
            Files.copy(file.getInputStream(),Paths.get(CoursePRFolder).resolve(url));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }


    }


    @Override
    public void addCardConceptuelFile(MultipartFile file , String url) {
        try {
            Files.copy(file.getInputStream(),Paths.get(conceptuelCardFolder).resolve(url));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void addCourse(MultipartFile file , String url) {
        try {
            Files.copy(file.getInputStream(),Paths.get(coureseFolder).resolve(url));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    @Override
    public void  addBlockFiles(MultipartFile file , String url) {
        try {

                Files.copy(file.getInputStream(), Paths.get(BlockFilesFolder).resolve(url));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    @Override
    public void saveClubArticle(MultipartFile file , String url) {
        try {
            Files.copy(file.getInputStream(),Paths.get(articleClubFolder).resolve(url));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    @Override
    public Resource load(String filename) {
        try {
            Path file = Paths.get(clubFolder).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    } @Override
    public Resource loadArticle(String filename) {
        try {
            Path file = Paths.get(articleClubFolder).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Resource loadExerciceFile(String filename) {
        try {
            Path file = Paths.get(ExerciceFolder).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    public Resource loadBlockFile(String filename) {
        try {
            Path file = Paths.get(ExerciceFolder).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    @Override
    public Resource loadCart(String filename) {
        try {
            Path file = Paths.get(conceptuelCardFolder).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    @Override
    public List<Resource> loadFileClub(List<String> filenamesList) {
        List<Resource> fileslist = new ArrayList<>() ;
        try {
            for (String filename: filenamesList
                 ) {
                Path file = Paths.get(conceptuelCardFolder).resolve(filename);
                Resource resource = new UrlResource(file.toUri());
                if(resource.exists() || resource.isReadable()) {
                    fileslist.add(resource) ;
                }else {
                    throw new RuntimeException("Could not read the file!");
                }
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return fileslist;
    }

    @Override
    public Resource loadCoursePR(String filename) {
        try {
            Path file = Paths.get(CoursePRFolder).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    @Override
    public Resource loadCourse(String filename) {
        try {
            Path file = Paths.get(coureseFolder).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(clubFolder).toFile());
    }
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(Paths.get(clubFolder));
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

}
