package com.example.backendproject.entities;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Lob;

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
    @Column(name = "type" , columnDefinition = "TEXT" )
    private String type;
    @Column(name = "isOptinal"  ,nullable = false)
    private Boolean isOptinal;

//    private String customerAttributeJSON;
    @Lob
    private String blockParams;

    @OneToOne
    private User_answer user_answer;
//
//    @OneToMany(mappedBy = "block" ,  fetch = FetchType.EAGER,   orphanRemoval=true , cascade = CascadeType.REMOVE)
//    private List<BlockFile> blockFileList;

//
//    @OneToMany(mappedBy="block")
//    private List<Exercice_Block> exercice_blockList;

}
