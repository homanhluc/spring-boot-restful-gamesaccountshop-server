package com.lucho.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucho.domain.web.User;
import com.lucho.service.RoleService;
import com.lucho.service.UserService;

@RestController
@CrossOrigin("*")
public class ApiUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/api/v1/login")
	public ResponseEntity<com.lucho.domain.api.User> login(@RequestBody com.lucho.domain.api.User user) {
		return new ResponseEntity<>(userService.checkLogin(user), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/api/v1/register")
	public ResponseEntity<Boolean> register(@RequestBody com.lucho.domain.api.User user) {
		User dbUser = new User();
		dbUser.setName(user.getName());
		dbUser.setEmail(user.getEmail());
		dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
		dbUser.addRole(roleService.findByName("ROLE_CUSTOMER"));
		
    	return new ResponseEntity<>(userService.register(dbUser), HttpStatus.CREATED);
	}

}
