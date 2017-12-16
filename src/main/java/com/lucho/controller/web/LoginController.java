package com.lucho.controller.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {

	@PostMapping("/api/login")
	public ResponseEntity login() {
		return new ResponseEntity<>("Login success",HttpStatus.OK);
	}
	
}
