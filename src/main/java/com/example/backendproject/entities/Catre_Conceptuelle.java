package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catre_Conceptuelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Carte_id;
    private String name;
    private String  url;
//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.REMOVE)
//    Chapter chapter;

}
