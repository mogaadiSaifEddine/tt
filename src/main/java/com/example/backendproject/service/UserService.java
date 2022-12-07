package com.example.backendproject.service;

import java.util.List;

import com.example.backendproject.entities.Parent;
import com.example.backendproject.entities.Role;
import com.example.backendproject.entities.User;


public interface UserService {
	Role addRole(Role role);
	User  addRoleToUser(String username, String rolename);
	List<User> findAllUsers();
	User register(User user);

		Parent addParent(Parent parent);

}
