package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pre_Required_Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Pre_Required_Chapter_Id;
    private String name;
  @JsonIgnore
  @ManyToOne
  private Chapter chapter;
}
