package com.example.backendproject.service;


import com.example.backendproject.entities.User_answer;
import com.example.backendproject.repos.UserAnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserAnswerService {
    @Autowired
    UserAnswerRepo userAnswerRepo;
    public User_answer AddUserAnswer(User_answer user_answer){
        return userAnswerRepo.save(user_answer);
    }
    public User_answer UpdateUserAnswer(User_answer user_answer){
        return userAnswerRepo.save(user_answer);
    }
    public void DeleteUserAnswer(Long id){
        userAnswerRepo.deleteById(id);
    }
    public List<User_answer> GetAllUserAnswer(){
        return userAnswerRepo.findAll();
    }


}
