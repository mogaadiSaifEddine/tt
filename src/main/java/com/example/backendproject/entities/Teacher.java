package com.example.backendproject.entities;


import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorValue("teacher")
public class Teacher extends User{


@ManyToMany(mappedBy = "teacherList")
@ToString.Exclude
List<Group> groupTeacher  ;





}
