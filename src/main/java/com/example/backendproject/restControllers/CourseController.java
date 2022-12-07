package com.example.backendproject.restControllers;

import com.example.backendproject.entities.Catre_Conceptuelle;
import com.example.backendproject.entities.Course;
import com.example.backendproject.repos.CourserRepo;
import com.example.backendproject.service.CourseService;
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
@RequestMapping("/chapter")

public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourserRepo courserRepo;


    @Autowired
    FileStorageService fileStorageService;
    @PostMapping("/course/{idChapter}")
    public Course AddCourse(@PathVariable("idChapter") Long idChapter, @RequestParam("file") MultipartFile file) {
        return courseService.AddCourse(file, idChapter);
    }
//    @PutMapping()
//    public Course UpdateCourse(@RequestBody Course course) {
//        return courseService.UpdateCourse(course);
//    }
//    @DeleteMapping("/{id}")
//    public void DeleteCourse(@PathVariable("id") Long id) {
//        courseService.DeleteCourse(id);
//    }
//    @GetMapping()
//    public List<Course> GetAllCourse() {
//        return courseService.GetAllCourse();
//    }
@GetMapping("/course/{idFile}")
@ResponseBody
public ResponseEntity<Resource> getCarte(@PathVariable Long idFile){

    Course fileDB = courserRepo.findById(idFile).orElse(null);
    assert fileDB != null;
    String  filename = fileDB.getUrl();
    Resource file = fileStorageService.loadCourse(filename);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
}






}
