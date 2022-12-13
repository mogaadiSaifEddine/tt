package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exrcise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ex_id;

    @Column(name = "name" , columnDefinition = "TEXT" )
    private String name;
    @Column(name = "difficulty" , columnDefinition = "TEXT" ,nullable = false)

    private Diffuculty difficulty;
    @Column(name = "type" , columnDefinition = "TEXT" ,nullable = false)
    private ExerciceType type;

    private Long exerciceorder;
    private String question ;

    private boolean isRTL ;
//    @ManyToOne
//    private User user;




    @JsonIgnoreProperties("exercice")
    @OneToMany(mappedBy="exercice"  , cascade = CascadeType.REMOVE)
    List<Exercice_Block> blocks;




    @OneToMany(mappedBy="exercice",orphanRemoval=true)
    @JsonIgnore
    private List<User_answer> user_answers;


    @ManyToOne
    @JoinColumn(name = "exerciceSerie")
    @JsonIgnore
    private ExerciceSerie exerciceSerie;

    @OneToOne()
    @JoinColumn(name = "file")
    private    ExerciceFile exerciceFile;


}
