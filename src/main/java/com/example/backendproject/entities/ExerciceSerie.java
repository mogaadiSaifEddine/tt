package com.example.backendproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ExerciceSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String image;
    private String type;
    @OneToMany(mappedBy = "exerciceSerie"  , cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"exerciceSerie", "user_answers","exercice_blockList"})
    @ToString.Exclude
//
    private List<Exrcise> exercices;

  private   SeriesType seriesType;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Chapter chapter;

    public ExerciceSerie(SeriesType seriesType) {
        this.seriesType = seriesType;
    }
}
