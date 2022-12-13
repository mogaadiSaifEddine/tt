package com.example.backendproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "usertype")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	private String firstname;
	private String lastname;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String email;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date datenaissance;
	private Long phone;
	private String sexe;
	private String profession;
	private String password;
	private String confirmepassword;
	private Boolean isblocked;
	private Boolean enabled=false;
	@Column(name = "connected")
	private Boolean connected ;





	 @ManyToOne
	private  Role role;





}