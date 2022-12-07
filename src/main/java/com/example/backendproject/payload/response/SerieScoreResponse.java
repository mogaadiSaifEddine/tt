package com.example.backendproject.payload.response;


import com.example.backendproject.entities.User_answer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
public class SerieScoreResponse {
    private Long  scoreSerie ;
    private List <User_answer> userAnswersList ;

}
