package com.hotelService.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotelService.app.entity.Hotel;
import com.hotelService.app.exceptions.ResourceNotFoundException;
import com.hotelService.app.service.HotelService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/hotel")
@Slf4j
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/save")
	private ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
		log.info("Saving hotel in DB");
		Hotel savedHotel=hotelService.saveHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
	}
	
	@GetMapping("/getAll")
	private ResponseEntity<List<Hotel>> getHotelsList(){
		log.info("Fetching all hotel data from DB");
		List<Hotel> hotelsList =hotelService.getHotelsList();
		return ResponseEntity.ok(hotelsList);
	}
	
	@GetMapping("/findById/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") Integer hotelId) throws ResourceNotFoundException{
		log.info("Fetching hotel data from DB for HotelId : "+hotelId);
		Hotel getHotel=hotelService.getHotelById(hotelId);
		return ResponseEntity.ok(getHotel);
	}
	
	@PutMapping("/updateHotel/{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable("hotelId") Integer hotelId, @RequestBody Hotel hotel) throws ResourceNotFoundException{
		log.info("Updating hotel data");
		Hotel updatedHotel=hotelService.udateHotel(hotelId, hotel);
		return ResponseEntity.ok(updatedHotel);
	}
	
	@DeleteMapping("/delete/{hotelId}")
	public void deleteHotel(@PathVariable Integer hotelId) {
		log.info("Deeting hotel from DB");
		hotelService.delteHotelById(hotelId);
	}
}











