package com.example.backendproject.restControllers;

import com.example.backendproject.entities.*;
import com.example.backendproject.payload.request.UserAnswerMinRequest;
import com.example.backendproject.payload.request.UserAnswerRequest;
import com.example.backendproject.payload.response.LastStep;
import com.example.backendproject.payload.response.SerieScoreResponse;
import com.example.backendproject.repos.*;
import com.example.backendproject.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/UserAnswer")


public class User_Answer_Controller {
    @Autowired
    UserAnswerService userAnswerService;
    @Autowired
    ExerciceSerieRepo exerciceSerieRepo;

    @Autowired
    private UserAnswerRepo userAnswerRepo;
    @Autowired
    ExercBlockRepository exercBlockRepository;


    @Autowired
    ExrciceRepository exrciceRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository ;

    @PostMapping("/")
    public User_answer AddUserAnswer(@RequestBody UserAnswerRequest user_answer) {
        User_answer user_answer1 = new User_answer();


        Exrcise exrcise = exrciceRepository.findById(user_answer.getExerciceID()).orElse(null) ;
        User user  =  userRepository.findById(user_answer.getUserID()).orElse(null) ;
//        User_answer userAnswerSAved  = userAnswerRepo.findByStudentAndExercice(user , exrcise);
//
//        System.out.println("----------------------------------------------------------");
//        System.out.println(userAnswerSAved.toString());
//        System.out.println("-------------------------------------------------------------");
//        userAnswerRepo.delete(userAnswerSAved);

        user_answer1.setExercice(exrciceRepository.findById(user_answer.getExerciceID()).orElse(null));
        user_answer1.setStudent((Student) studentRepository.findById(user_answer.getUserID()).orElse(null));
        user_answer1.setScore(user_answer.getScore());
        user_answer1.setSerie(user_answer.getSerie());
        userAnswerService.AddUserAnswer(user_answer1);
        return user_answer1;
    }
    @PutMapping("/")
    public User_answer UpdateUserAnswer(@RequestBody User_answer user_answer) {
        return userAnswerService.UpdateUserAnswer(user_answer);
    }
    @DeleteMapping("/{id}")
    public void DeleteUserAnswer(@PathVariable("id") Long id) {
        userAnswerService.DeleteUserAnswer(id);
    }
    @GetMapping("/")
    public List<User_answer> GetAllUserAnswer() {
        return userAnswerService.GetAllUserAnswer();
    }

    @GetMapping("/{id}")
    public List<User_answer> GetuserANswerByUser(@PathVariable Long id) {
        System.out.println(id);
        System.out.println("dsdsdsdsdsds");
        Student user = (Student) studentRepository.findById(id).orElse(null);
        return userAnswerRepo.findByStudent(user);
    }
    @GetMapping("/score/{id}/{serie_exercice_id}")
    public SerieScoreResponse getScore(@PathVariable("id") Long id , @PathVariable("serie_exercice_id") Long serie_exercice_id) {
        ExerciceSerie exerciceSerie = exerciceSerieRepo.findById(serie_exercice_id).orElse(null);

        SerieScoreResponse serieScoreResponse = new SerieScoreResponse() ;
        List <User_answer> user_answersList  = new ArrayList<>() ;
        Long score =0L;
        assert exerciceSerie != null;

        User user = userRepository.findById(id).orElse(null);
        serieScoreResponse.setUserAnswersList(user_answersList);
        serieScoreResponse.setScoreSerie((score/user_answersList.size())*100);
        return  serieScoreResponse;
    }



    @GetMapping("/last/{id}")
    public LastStep getLastUserAnswer(@PathVariable Long id) {
        System.out.println(id);
        System.out.println("dsdsdsdsdsds");
        Student user = (Student) studentRepository.findById(id).orElse(null);
        List <User_answer> l = userAnswerRepo.findByStudent(user) ;
        Optional<User_answer> last =  l.stream().reduce((first, second) -> second);
        Long value  = last.map(user_answer -> user_answer.getExercice().getEx_id()).orElse(null);

    LastStep lastStep  = new LastStep() ;
    lastStep.setId(value);
    lastStep.setSerie(last.map(User_answer::getSerie).orElse(null)) ;
        return lastStep;
    }

//    @GetMapping("/useranswerlast/{userId}")
//    User_answer getLastUserAnswer(@PathVariable Long userId){
//
//
//        return  this.userANs
//    }





}
