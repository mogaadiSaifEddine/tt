package com.example.backendproject.restControllers;

import com.example.backendproject.entities.Course;
import com.example.backendproject.entities.CoursePR;
import com.example.backendproject.repos.CoursePRRepos;
import com.example.backendproject.repos.CourserRepo;
import com.example.backendproject.service.CoursePRService;
import com.example.backendproject.service.CourseService;
import com.example.backendproject.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/chapter")

public class CoursePRController {
    @Autowired
    CoursePRService courseService;

    @Autowired
    CoursePRRepos courserRepo;


    @Autowired
    FileStorageService fileStorageService;
    @PostMapping("/course_pr/{idChapter}")
    public CoursePR AddCourse(@PathVariable("idChapter") Long idChapter, @RequestParam("file") MultipartFile file) {
        return courseService.AddCourse(file, idChapter);
    }

//    }
@GetMapping("/course_pr/{idFile}")
@ResponseBody
public ResponseEntity<Resource> getCarte(@PathVariable Long idFile){

    CoursePR fileDB = courserRepo.findById(idFile).orElse(null);
    assert fileDB != null;
    String  filename = fileDB.getUrl();
    Resource file = fileStorageService.loadCoursePR(filename);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
}






}
