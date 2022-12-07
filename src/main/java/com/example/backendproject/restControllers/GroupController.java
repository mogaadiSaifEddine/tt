package com.example.backendproject.restControllers;


import com.example.backendproject.entities.Chapter;
import com.example.backendproject.entities.Group;
import com.example.backendproject.entities.User;
import com.example.backendproject.repos.ChapterRepo;
import com.example.backendproject.repos.GroupRepops;
import com.example.backendproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepops groupRepops;
    @Autowired
    UserRepository userRepository  ;
    @Autowired
    ChapterRepo chapterRepo ;
    @PostMapping()
    public Group addGroup (@RequestBody Group group){
    Group grp = groupRepops.save(group);
        if (!group.getChaptersList().isEmpty()){
            for (Chapter chapter: group.getChaptersList()
                 ) {
                chapter.setGroup(grp);
                chapterRepo.save(chapter);

            }
        }
        return  grp;
    }
    @PutMapping("/")
    public Group updateGroup (@RequestBody Group group){
        Group oldGrp = groupRepops.findById(group.getId()).orElse(null);
        assert oldGrp != null;
        oldGrp.setName(group.getName());
        oldGrp.setDescription(group.getDescription());

        return  groupRepops.save(oldGrp);
    }


    @DeleteMapping("/{id}")
    public Boolean deleteGroup (@PathVariable Long id){
        groupRepops.deleteById(id);
        return  true ;
    }
    @GetMapping("/{userID}")
    public Group getGroupsByUser (@PathVariable Long userID){
        User user  = userRepository.findById(userID).orElse(null);
        return  groupRepops.findGroupByUsers(user);
    }
    @GetMapping()
    public List<Group> getAllGroups (){
        return  groupRepops.findAll() ;
    }
}
