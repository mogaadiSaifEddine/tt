package com.example.backendproject.restControllers;

import com.example.backendproject.entities.Module;
import com.example.backendproject.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/Module")

public class ModuleController {
    @Autowired
    ModuleService moduleService;
    @PostMapping()
    public Module AddModule(@RequestBody Module module) {
        return moduleService.AddModule(module);
    }
    @PutMapping()
    public Module UpdateModule(@RequestBody Module module) {
        return moduleService.UpdateModule(module);
    }
    @DeleteMapping("{id}")
    public void DeleteModule(@PathVariable("id") Long id) {
        moduleService.DeleteModule(id);
    }
    @GetMapping()
    public List<Module> GetAllModule() {
        return moduleService.GetAllModule();
    }







}
