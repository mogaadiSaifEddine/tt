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
public class Exercice_Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Exercice_Block_Id;
    private String label;
    private String placeholder;
    private String value;
    private String correctValue ;

    private Boolean isAdmissable;
    private int blockOrder;


    private BlockType exerciceBlockType;

    @ManyToOne
    @JoinColumn(name="exercice")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private  Exrcise exercice;

}
