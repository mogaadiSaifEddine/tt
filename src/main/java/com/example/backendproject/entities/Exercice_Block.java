package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Lob
    private String blockParams;
    private BlockType exerciceBlockType;
    @Column(name = "tags", columnDefinition = "jsonb")
    @JsonProperty("tags")
    private Map<String,Object> tags = new HashMap<>();
    @ManyToOne
    @JoinColumn(name="exercice")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private  Exrcise exercice;


    @OneToMany(mappedBy = "block" ,  fetch = FetchType.EAGER,   orphanRemoval=true)
    private List<BlockFile> blockFileList;

}
