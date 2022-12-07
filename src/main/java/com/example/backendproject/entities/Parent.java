package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("parent")

public class Parent extends  User{

    @OneToMany(mappedBy = "parent")
    @JsonIgnoreProperties({"parent" , "studentList"})
    List<Coupon> couponList ;

    @OneToMany(mappedBy = "parent")
    @JsonIgnoreProperties({"parent"  , "coupon"})
    List<Student> studentList ;






}
