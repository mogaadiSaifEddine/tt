package com.example.backendproject.entities;


import javax.persistence.*;

@Entity
@Table(name = "carte_exercice")
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String name;
    private  String url;

    @ManyToOne
    @JoinColumn(name = "exercice_id",referencedColumnName = "ex_id")
    private Exrcise exercice;



}
