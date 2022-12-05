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

import com.hotelService.app.entity.Rating;
import com.hotelService.app.exceptions.ResourceNotFoundException;
import com.hotelService.app.service.RatingService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/rating")
@Slf4j
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
		log.info("saving rating in DB");
		Rating savedRating=ratingService.saveRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);
	}
	
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getRatingById(@PathVariable("ratingId") Integer ratingId) throws ResourceNotFoundException {
		log.info("Fetching Rating from DB for ratingId : "+ratingId);
		Rating getRating=ratingService.getRating(ratingId);
		return ResponseEntity.ok(getRating);
	}
	
	@GetMapping("/getAllRating")
	public ResponseEntity<List<Rating>> getAllRating(){
		log.info("Fetching all Rating from DB");
		List<Rating> listOfRating=ratingService.getAll();
		return ResponseEntity.ok(listOfRating);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userId") Integer userId) {
		log.info("Fetching Rating from DB for userId : "+userId);
		List<Rating> getRating=ratingService.getRatingByUserId(userId);
		return ResponseEntity.ok(getRating);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByhotelId(@PathVariable("hotelId") Integer hotelId) {
		log.info("Fetching Rating from DB for hotelId : "+hotelId);
		List<Rating> getRating=ratingService.getRatingByhotelId(hotelId);
		return ResponseEntity.ok(getRating);
	}
	
	@PutMapping("/updateRating/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") Integer ratingId, @RequestBody Rating rating) throws ResourceNotFoundException{
		log.info("Updating rating of ratingId : "+ratingId);
		Rating updatedRating=ratingService.updateRating(ratingId,rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedRating);
	}
	
	@DeleteMapping("/delete/{ratingId}")
	public void deleteRating(@PathVariable("ratingId") Integer ratingId) {
		log.info("Updating rating ");
		ratingService.deleteRating(ratingId);
	}
}











