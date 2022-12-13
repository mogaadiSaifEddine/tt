package com.example.backendproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorValue("student")

public class Student extends  User{
    @ManyToOne
    @JoinColumn(name="group_id")
    private  Group group;


    @OneToMany(mappedBy="student")
    @ToString.Exclude
    private List<User_answer> user_answers;


    @ManyToMany()
    @JsonIgnoreProperties({"studentList , parent"})
    @ToString.Exclude
    List<Coupon> coupon ;

    @ManyToOne
    @JsonIgnoreProperties({"studentList" , "couponList"})
    Parent parent ;

    }
