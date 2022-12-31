package com.example.backendproject.entities;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Lob;

import java.util.List;
import java.util.Map;

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
//    private String customerAttributeJSON;
    @Lob
    private String blockParams;

    @OneToMany(mappedBy = "block" ,  fetch = FetchType.EAGER,   orphanRemoval=true)
    private List<BlockFile> blockFileList;

//
//    @OneToMany(mappedBy="block")
//    private List<Exercice_Block> exercice_blockList;

}
