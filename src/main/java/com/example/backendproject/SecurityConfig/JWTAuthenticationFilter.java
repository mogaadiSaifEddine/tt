package com.example.backendproject.SecurityConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.backendproject.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		User user = null;
			
			try {
				user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			} catch (IOException e) {
					e.printStackTrace();
			}

		assert user != null;
		return authenticationManager.
			  authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));	
	}

	@Override
	@CrossOrigin(origins = "*")
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			                                FilterChain chain,	Authentication authResult) 
			                                throws IOException, ServletException {
		
		org.springframework.security.core.userdetails.User springUser =  
		 (org.springframework.security.core.userdetails.User)		authResult.getPrincipal();
		
		List<String> roles = new ArrayList<>();
		
		springUser.getAuthorities().forEach(au -> {
			roles.add(au.getAuthority());
		});
		
		String jwt = JWT.create().
				withSubject(springUser.getUsername()).
				withArrayClaim("roles", roles.toArray(new String[roles.size()])).
				withExpiresAt(new Date(System.currentTimeMillis()+SecParams.EXP_TIME)).
				sign(Algorithm.HMAC256(SecParams.SECRET));
		String userco=authResult.getName();
		UserDetails userDetails = (UserDetails) authResult.getPrincipal();
		System.out.println("userConnected"+authResult.getName());
		response.addHeader("Authorization", jwt);
		response.addHeader("userconnected", userco);
		response.addHeader("principal", String.valueOf(userDetails));

	}
	
	
  
	

}
