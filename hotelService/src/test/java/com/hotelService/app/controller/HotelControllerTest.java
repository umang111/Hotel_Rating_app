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
import com.hotelService.app.entity.Hotel;
import com.hotelService.app.exceptions.ResourceNotFoundException;
import com.hotelService.app.service.HotelService;

@WebMvcTest
class HotelControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private HotelService hotelService;
	
	private Hotel hotel;
	
	@BeforeEach
	private void setup() {
		hotel=getHotel();
	}
	
	@Test
	public void test_saveHotel() throws JsonProcessingException, Exception {
		when(hotelService.saveHotel(hotel)).thenReturn(hotel);
		ResultActions response=mockMvc.perform(post("/hotel/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(hotel)));
	}
	
	@Test
	public void test_getHotelList() throws Exception {
		List<Hotel> hotels=new ArrayList<>();
		hotels.add(hotel);
		hotels.add(hotel);
		when(hotelService.getHotelsList()).thenReturn(hotels);
		ResultActions response = mockMvc.perform(get("/hotel/getAll"));
	}

	@Test
	public void test_getHOtelById() throws ResourceNotFoundException, Exception {
	
		when(hotelService.getHotelById(1)).thenReturn(hotel);
		ResultActions response =mockMvc.perform(get("/hotel/findById/1"));
	}
	
	@Test
	public void test_updateHotel() throws JsonProcessingException, Exception, ResourceNotFoundException {
		when(hotelService.udateHotel(1, hotel)).thenReturn(hotel);
		mockMvc.perform(put("/hotel/updateHotel/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(hotel)));
	}
	
	@Test
	public void test_deleteHotel() throws Exception {
		mockMvc.perform(delete("/hotel/delete/1"));
		verify(hotelService,times(1)).delteHotelById(1);
	}
	
	private Hotel getHotel() {
		Hotel hotel=new Hotel();
		hotel.setHotelId(1);
		hotel.setName("Taj Hotel");
		hotel.setLocation("delhi");
		hotel.setAbout("Toj is a good Hotel");
		
		return hotel;
		
	}
}










