package com.example.backendproject.service;

import com.example.backendproject.entities.Block;
import com.example.backendproject.entities.Pre_Required_Chapter;
import com.example.backendproject.repos.BlockRepo;
import com.example.backendproject.repos.Pre_Required_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Pre_Required_Service {
    @Autowired
    Pre_Required_Repo pre_required_repo;
    public Pre_Required_Chapter Addprerequired(Pre_Required_Chapter prerequired){
        return pre_required_repo.save(prerequired);
    }
    public Pre_Required_Chapter UpdatePreReaquired(Pre_Required_Chapter prerequired){
        return pre_required_repo.save(prerequired);
    }
    public void Deleteperequired(Long id){
        pre_required_repo.deleteById(id);
    }
    public List<Pre_Required_Chapter> GetAllPrerequired(){
        return pre_required_repo.findAll();
    }
}
