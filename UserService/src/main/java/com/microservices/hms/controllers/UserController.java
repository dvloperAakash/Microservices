package com.microservices.hms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.hms.entities.User;
import com.microservices.hms.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	// create
	@PostMapping("/add")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User curr_user = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(curr_user);
	}

	// get single user
	@GetMapping("/{id}")
	public ResponseEntity<User> getSingleUser(@PathVariable("id") String id) {
		User user = userService.getUser(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}
	
	// get all user
	@GetMapping
	public ResponseEntity<List<User>>getAllUser(){
		List<User>users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}
}











