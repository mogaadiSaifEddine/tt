package com.example.backendproject.entities;


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
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String logo;
    private Long numberOfParticipants;

    public Club(Object user) {

    }
    @OneToMany(mappedBy = "club")
    @JsonIgnoreProperties("club")
    private List<ArticleClub> articleClubs;
}
