package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapter_id;
    private String name;
    private  String url ;
    private ChapterType chapterType;

    private Trimestre trimestre;

    @JsonIgnore
    @ManyToOne
    private User teacher ;


    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "catre_conceptuelle_carte_id")
    private    Catre_Conceptuelle catre_conceptuelle;


    @OneToOne()
    @JoinColumn(name = "course")
    private    Course course;





    @ManyToOne
   @JsonIgnoreProperties("chaptersList")
   private Group group;





    @OneToMany(mappedBy = "parent" ,  fetch = FetchType.EAGER,   orphanRemoval=true)
    @ToString.Exclude
    private List<Chapter> children;

    @ManyToOne( )
    @ToString.Exclude
    @JsonIgnoreProperties({"children" , "group"})
    private Chapter parent;

    @OneToMany  (  mappedBy = "chapter", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<ExerciceSerie> exerciceSeries;
    @OneToMany(mappedBy = "chapter" )
    @JsonIgnoreProperties("chapter")
    @ToString.Exclude
    private  List<Catre_Conceptuelle> catre_conceptuelles;
    @OneToMany(mappedBy = "chapter" )
    @JsonIgnoreProperties("chapter")
    @ToString.Exclude
     private  List<Resumer_Cour> resumer_courList;
    @OneToMany(mappedBy = "chapter" )
    @JsonIgnoreProperties("chapter")
    @ToString.Exclude
    private  List<Pre_Required_Chapter> pre_required_chapterList;





}
