package com.hotelService.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelService.app.entity.Rating;
import com.hotelService.app.exceptions.ResourceNotFoundException;
import com.hotelService.app.service.RatingService;

@WebMvcTest
class RatingControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private RatingService ratingService;
	
	@Autowired
	private RatingController ratingController;
	
	private Rating rating;
	
	@BeforeEach
	public void setup() {
		rating=getRating();
	}
	
	@Test
	public void test_saveRating() throws JsonProcessingException, Exception {
		when(ratingService.saveRating(rating)).thenReturn(rating);
		ResultActions response =mockMvc.perform(post("/rating/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(rating)));
	}
	
	@Test
	public void test_getRatingById() throws Exception, ResourceNotFoundException {
		when(ratingService.getRating(1)).thenReturn(rating);
		ResultActions response =mockMvc.perform(get("/rating/1"));
	}
	
	@Test
	public void test_getAll() throws Exception {
		List<Rating> ratings=new ArrayList<>();
		ratings.add(rating);
		ratings.add(rating);
		when(ratingService.getAll()).thenReturn(ratings);
		ResultActions response =mockMvc.perform(get("/rating/getAllRating"));	
	}
	
	@Test
	public void test_getRatingByUserId() throws Exception, ResourceNotFoundException {
		List<Rating> ratings=new ArrayList<>();
		ratings.add(rating);
		ratings.add(rating);
		when(ratingService.getRatingByUserId(2)).thenReturn(ratings);
		ResultActions response =mockMvc.perform(get("/rating/user/2"));
	}
	
	@Test
	public void test_getRatingByhotelId() throws Exception, ResourceNotFoundException {
		List<Rating> ratings=new ArrayList<>();
		ratings.add(rating);
		ratings.add(rating);
		when(ratingService.getRatingByhotelId(5)).thenReturn(ratings);
		ResultActions response =mockMvc.perform(get("/rating/hotel/5"));
	}
	
	@Test
	public void test_updateRating() throws ResourceNotFoundException, JsonProcessingException, Exception {
		when(ratingService.updateRating(1, rating)).thenReturn(rating);
		ResultActions response =mockMvc.perform(put("/rating/updateRating/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(rating)));
	}
	
	@Test
	public void test_deleteRating() throws Exception {
		ResultActions response =mockMvc.perform(delete("/rating/delete/1"));
		verify(ratingService,times(1)).deleteRating(1);
	}
	
	private Rating getRating() {

		Rating rating =new Rating();
		rating.setRatingid(1);
		rating.setUserId(2);
		rating.setHotelId(5);
		rating.setRating(9);
		rating.setFeedback("good");
		return rating;
	}

}
