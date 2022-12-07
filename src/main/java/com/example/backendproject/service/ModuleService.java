package com.example.backendproject.service;


import com.example.backendproject.entities.Module;
import com.example.backendproject.repos.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModuleService {
    @Autowired
    ModuleRepo moduleRepo;
    public Module AddModule(Module module){
        return moduleRepo.save(module);
    }
    public Module UpdateModule(Module module){
        return moduleRepo.save(module);
    }
    public void DeleteModule(Long id){
        moduleRepo.deleteById(id);
    }
    public List<Module> GetAllModule(){
        return moduleRepo.findAll();
    }


}
