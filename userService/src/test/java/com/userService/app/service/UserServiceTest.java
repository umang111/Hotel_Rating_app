package com.userService.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.userService.app.entity.User;
import com.userService.app.exceptions.ResourceNotFoundException;
import com.userService.app.repository.UserRepository;

@SpringBootTest
class UserServiceTest {
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	private User user;
	

	@BeforeEach
	public void setUp() {
		
		user =User.builder().userId(1)
				.userName("umang garg")
				.email("umang@gmail.com")
				.about("about user")
				.build();
	}
	 
	@Test
	public void test_saveUser() {
		when(userRepository.save(user)).thenReturn(user);
		User getUser=userService.saveUser(user);
		assertEquals(1, getUser.getUserId());
	}
	
	@Test
	public void test_getUserById_doesNotThrowException() throws ResourceNotFoundException {
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		User getUser=userService.getUserById(1);
		assertEquals(1, getUser.getUserId());
	}

	@Test
	public void test_getUserById_ThrowsException() throws ResourceNotFoundException {
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		User getUser=userService.getUserById(2);
	}
	
	
	@Test
	public void test_getAllUser() {
		List<User>users=new ArrayList<>();
		users.add(user);
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);
		userService.getAllUser();
	}
	
	
	@Test
	public void test_updateUser() {
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		try {
			userService.updateUser(1, user);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_deleteUser() {
		userService.deleteUser(1);
		verify(userRepository,times(1)).deleteById(1);
	}
	
}









