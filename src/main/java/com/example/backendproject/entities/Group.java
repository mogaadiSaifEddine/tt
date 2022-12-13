package com.example.backendproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "group_eleve")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name  ;
    private  String description ;


    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private List<Student> users;


    @OneToMany (mappedBy = "group")
    @JsonIgnore
    private List <Chapter>chaptersList;

    @ManyToMany

    List<Teacher> teacherList ;


}
