package com.example.backendproject.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User_answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_answer_id;
    @Column(columnDefinition="varchar(255) default 'false'")
    private String value;

    private Long score;



    private String serie ;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user", nullable=false)
    private Student student;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="exercice" )
    private Exrcise exercice;


}
