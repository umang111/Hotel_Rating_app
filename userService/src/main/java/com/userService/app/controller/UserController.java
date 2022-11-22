package com.userService.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userService.app.entity.User;
import com.userService.app.exceptions.ResourceNotFoundException;
import com.userService.app.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		log.info("start: saving user in db");
		User savedUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId) throws ResourceNotFoundException {
		log.info("start: finding user by userid: " +userId);
		User userById = userService.getUserById(userId);
		return ResponseEntity.ok(userById);
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() {
		log.info("fetching all users from DB");
		List<User> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User user) throws ResourceNotFoundException {
		log.info("start: updating user with user id " + userId);
		User updatedUser = userService.updateUser(userId, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Integer userId) {
		log.info("deleting user from db userId:"+userId);
		userService.deleteUser(userId);
	}
}

















