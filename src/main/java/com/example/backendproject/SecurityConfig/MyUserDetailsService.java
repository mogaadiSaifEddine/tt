package com.example.backendproject.SecurityConfig;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.backendproject.entities.Coupon;
import com.example.backendproject.entities.Student;
import com.example.backendproject.entities.User;
import com.example.backendproject.repos.UserRepository;
import com.example.backendproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user==null)
			throw new UsernameNotFoundException("Utilisateur introuvable!");
		if (user.getProfession().equals("student")) {
			Student student = (Student) user;


			boolean allNonValid = student.getCoupon().stream().allMatch(coupon -> {
				return coupon.getExpirationDate().before(new Date());
			});

			if (allNonValid) {
				throw new UsernameNotFoundException("Coupon invalid!");

			}

		}
		List<GrantedAuthority> auths = new ArrayList<>();
		return new org.springframework.security.core.
		  userdetails.User(user.getUsername(), user.getPassword(), auths);
	
	}

}
