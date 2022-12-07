package com.example.backendproject.entities;


import javax.persistence.*;

@Entity
@Table(name = "file_club")
public class FileClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String name;
    private  String url;

    @ManyToOne
    @JoinColumn(name = "club_id",referencedColumnName = "id")
    private Club club;



}
