package com.example.backendproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long block_id;
    @Column(name = "name" , columnDefinition = "TEXT" ,nullable = false)
    private String name;
    @Column(name = "type" , columnDefinition = "TEXT" ,nullable = false)
    private String type;
    @Column(name = "isOptinal"  ,nullable = false)
    private Boolean isOptinal;
    @OneToOne
    private User_answer user_answer;
//
//    @OneToMany(mappedBy="block")
//    private List<Exercice_Block> exercice_blockList;

}
