package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article_club")
@Getter
@Setter
public class ArticleClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)



    private Long feed_id;
     String type;
     String description;
     Date date;
    String post;
    String image;


    @ManyToOne
    @JsonIgnoreProperties("articleClubs")
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    private User user;
}
