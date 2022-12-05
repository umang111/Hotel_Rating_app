package com.userService.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userService.app.entity.User;
import com.userService.app.exceptions.ResourceNotFoundException;
import com.userService.app.service.UserService;

@WebMvcTest
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
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
	public void test_saveUser() throws JsonProcessingException, Exception {
		when(userService.saveUser(user)).thenReturn(user);
		ResultActions response =mockMvc.perform(post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(user)));
	}
	
	@Test
	@DisplayName("JUnit test for getEmployeeById operation success")
	public void test_getUserbyId() throws Exception, ResourceNotFoundException {
		when(userService.getUserById(1)).thenReturn(user);
		ResultActions response =mockMvc.perform(get("/users/1"));
//		response.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("JUnit test for getEmployeeById operation failed")
	public void test_getUserbyId_exception() throws ResourceNotFoundException, Exception{
		when(userService.getUserById(1)).thenThrow(ResourceNotFoundException.class);
		ResultActions response =mockMvc.perform(get("/users/1"));
//		response.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void test_getAllUser() throws Exception {
		List<User>users=new ArrayList<>();
		users.add(user);
		users.add(user);
		when(userService.getAllUser()).thenReturn(users);
		ResultActions response =mockMvc.perform(get("/users/getAllUser"));
	}
	
	@Test
	public void test_updateUser() throws ResourceNotFoundException{
		when(userService.updateUser(anyInt(), any(User.class))).thenReturn(user);
		try {
			ResultActions response = mockMvc.perform(put("/users/update/1").contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(user)));
			response.andDo(print()).andExpect(status().isCreated()); //we can write this line to verify result also we can avoid this line 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void test_deleteUser() throws Exception {
		ResultActions response=mockMvc.perform(delete("/users/1"));
		verify(userService,times(1)).deleteUser(1);
	}
}














