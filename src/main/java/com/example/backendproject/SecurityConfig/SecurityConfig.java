		package com.example.backendproject.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).
				passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin","*"));
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login","/addrole","/addroleauser/{username}/{rolename}","/add","/group/","/forgetpassword/{email}","/resetpassword/{email}/{newpass}/{cofirm}","/activecompte/{username}","/all"
				,"/activecompte/{username}","/user/*","/niveau/{idNiveau}","/niveau/{idNiveau}","/niveau","/niveau","/niveau","/trimestre","/trimestre/{idTrimestre}","/sousChamp/{id_champ}","/sousChamp","/sousChamp/{idSousChamp}","/sousChamps/{idChamp}","/exercice/{idExercice}","/exercice","/exercice/{idExercice}"
				,"/difficulte","/difficulte/{idDifficulte}","/chapitre","/chapitre/{idChapitre}","/champ","/champ/{idChamp}","/exercice/{idExercice}","/exercice","/exercice").permitAll();
		//	http.authorizeRequests().antMatchers("/all").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter (authenticationManager())) ;
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


	}



}