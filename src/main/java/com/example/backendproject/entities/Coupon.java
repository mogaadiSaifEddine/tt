package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "coupon")
public class Coupon {

    @Id

    @Column(name = "id", nullable = false)
    private String id;
    private String value ;
    private Date creationDate ;
    private Date expirationDate ;
    private int numberUsers ;
    private int usedUsersNumber ;

    private CouponStatus status ;
    private  CouponType couponType ;

    @ManyToOne
    @JsonIgnoreProperties({"couponList", "studentList"})
    private  Parent parent ;
    @ManyToMany(mappedBy = "coupon")
    @JsonIgnoreProperties({"coupon", "parent" , "group","user_answers"})
    private List< Student> student ;



}
