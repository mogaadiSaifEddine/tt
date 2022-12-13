package com.example.backendproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resumer_Cour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Resumer_id;
    private String name;
    private String url;
}
