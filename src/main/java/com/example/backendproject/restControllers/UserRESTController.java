package com.example.backendproject.restControllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import com.example.backendproject.entities.*;

import com.example.backendproject.repos.GroupRepops;
import com.example.backendproject.repos.ParentRepository;
import com.example.backendproject.repos.StudentRepository;
import com.example.backendproject.repos.UserRepository;
import com.example.backendproject.service.CouponService;
import com.example.backendproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;



@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/user")

public class UserRESTController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	StudentRepository studentRepository ;
	@Autowired
	ParentRepository parentRepository ;

	@Autowired
	GroupRepops groupRepops;
	@Autowired
	CouponService couponService;



	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@RequestMapping(path="all",method=RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@PostMapping("/addrole")
	public Role Addrole(@RequestBody Role role){
		return userService.addRole(role);
	}
	@PostMapping("addroleauser/{username}/{rolename}")
	public void addroletouer(@PathVariable("username") String username,@PathVariable("rolename") String rolename){
		userService.addRoleToUser(username,rolename);
	}
	@PostMapping("/parent")
	public User addParent(@RequestBody Parent user) {

		user.setProfession("parent");
		return userService.register(user);
	}
	@PostMapping("/teacher")
	public User addTeacher(@RequestBody Teacher user) {

		user.setProfession("teacher");
		return userService.register(user);
	}

	@PostMapping("/student/{coupounId}")
	public User addStudent(@RequestBody Student user , @PathVariable  String  coupounId) {
		Coupon coupon =   couponService.getOneCoupon(coupounId);
		user.setProfession("student");
		coupon.setNumberUsers(coupon.getUsedUsersNumber()+1);
		List <Coupon> couponsList = new ArrayList<>();
		if (user.getCoupon()!=null) {
			couponsList = user.getCoupon();
		}
		couponsList.add(coupon);

		user.setCoupon(couponsList);
		user.setParent(coupon.getParent());
		return userService.register(user);
	}





/* SuperVIsor  */

//	@PostMapping("/student")
//	public User addStudent(@RequestBody Student user) {
//		return userService.register(user);
//	}
	@PutMapping("student/{id}")
	public User updateUser(@PathVariable Long id  , @RequestBody Student user){
		Student user1  = (Student) studentRepository.findById(id).orElse(null);
		assert user1!=null;
		user1.setFirstname(user.getFirstname());
		user1.setDatenaissance(user.getDatenaissance());

		user1.setLastname(user.getLastname());
		user1.setEmail(user.getEmail());
		user1.setGroup((user.getGroup()));

System.out.println(user.getPassword());

		if (user.getPassword() != null) {
//
			user1.setConfirmepassword(bCryptPasswordEncoder.encode(user.getPassword()));

			user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		return  userRepository.save(user1);
	}

	@DeleteMapping("/{username}" )

	public  void deleteUser(@PathVariable String username ){
	User user= userRepository.findByUsername(username);
		userRepository.delete(user);
	}

	@PostMapping("/forgetpassword/{email}")
	public User forgetPass(@PathVariable("email") String email){
		return userService.ForgetPassword(email);
	}

	@PostMapping("/resetpassword/{email}/{newpass}/{cofirm}")
	public void RestPassword(@PathVariable("email") String email,@PathVariable("newpass") String newpass,@PathVariable("cofirm") String cofirm) {
		userService.RestPassword(email,newpass,cofirm);
	}
	@GetMapping("/username/{username}")
	public User getUser(@PathVariable("username") String username) {




		User user =  userService.getUser(username);
		user.setConnected(true);
		userRepository.save(user);
		return  user ;
	}

	@PutMapping("/activecompte/{username}")
	public String activecompte(@PathVariable("username") String username) {
		userService.activecompte(username);
		return "activated";
	}

	@GetMapping("/students/{parentID}")
	public List <Student> getStudentsByParentID(@PathVariable Long parentID){

		Parent parent  = (Parent) parentRepository.findById(parentID).orElse(null);
		List <Student>  studentsList;

		studentsList = studentRepository.findByParent(parent);
		return studentsList ;




	}

	@PostMapping("/logout/{username}")
	public void logout(@PathVariable String username ){
		User user  = userRepository.findByUsername(username) ;
		assert user != null ;
		user.setConnected(false);
	 User user1 = 	userRepository.save(user) ;

	}
}
