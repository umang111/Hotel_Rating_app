package com.hotelService.app.service;

import static org.junit.jupiter.api.Assertions.*;
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

import com.hotelService.app.entity.Rating;
import com.hotelService.app.exceptions.ResourceNotFoundException;
import com.hotelService.app.repository.RatingRepository;

@SpringBootTest
class RatingServiceTest {

	@MockBean
	private RatingRepository ratingRepository;
	
	@Autowired
	private RatingService ratingService;
	
	private Rating rating;
	
	
	@BeforeEach
	public void setup() {
		rating=getRating();
	}
	
	@Test
	public void test_saveRating() {
		when(ratingRepository.save(rating)).thenReturn(rating);
		Rating savedRating= ratingService.saveRating(rating);
		assertEquals(1, savedRating.getRatingid());
	}
	
	@Test
	public void test_getAllRating() {
		List<Rating> ratings=new ArrayList<>();
		ratings.add(rating);
		ratings.add(rating);
		when(ratingRepository.findAll()).thenReturn(ratings);
		List<Rating> getALlRating=ratingService.getAll();
	}
	
	@Test
	public void test_getRatingByUserId() {
		List<Rating> ratings=new ArrayList<>();
		ratings.add(rating);
		ratings.add(rating);
		when(ratingRepository.findByUserId(2)).thenReturn(ratings);
		List<Rating> getALlRating=ratingService.getRatingByUserId(2);
	}
	
	@Test
	public void test_getRatingByhotelId() {
		List<Rating> ratings=new ArrayList<>();
		ratings.add(rating);
		ratings.add(rating);
		when(ratingRepository.findByHotelId(5)).thenReturn(ratings);
		List<Rating> getALlRating=ratingService.getRatingByhotelId(5);
	}
	
	@Test
	public void test_updateRating() throws ResourceNotFoundException {
		when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
		when(ratingRepository.save(rating)).thenReturn(rating);
		Rating ratingUpdateRating=ratingService.updateRating(1, rating);
		
	}
	
	@Test
	public void test_getRatingById() throws ResourceNotFoundException {
		when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
		Rating getRating=ratingService.getRating(1);
	}
	
	@Test
	public void test_DeleteRating() {
		
		ratingService.deleteRating(1);
		verify(ratingRepository,times(1)).deleteById(1);
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









