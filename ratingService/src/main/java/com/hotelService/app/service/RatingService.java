package com.hotelService.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelService.app.entity.Rating;
import com.hotelService.app.exceptions.ResourceNotFoundException;
import com.hotelService.app.repository.RatingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;

	public Rating saveRating(Rating rating) {
		Rating savedRating =ratingRepository.save(rating);
		log.info("Rating saved Successfully");
		return savedRating;
	}

	public Rating getRating(Integer ratingId) throws ResourceNotFoundException {

		Optional<Rating> getRating=ratingRepository.findById(ratingId);
		if (!getRating.isPresent()) {
			throw new ResourceNotFoundException("Rating not found for RatingId : "+ratingId);
		}
		Rating rating=new Rating();
		rating.setRatingid(getRating.get().getRatingid());
		rating.setUserId(getRating.get().getUserId());
		rating.setHotelId(getRating.get().getHotelId());
		rating.setRating(getRating.get().getRating());
		rating.setFeedback(getRating.get().getFeedback());
		return rating;
	}
	
	public List<Rating> getAll() {
		List<Rating> ratingList=ratingRepository.findAll();
		return ratingList;
	}

	public List<Rating> getRatingByUserId(Integer userId) {
		List<Rating> ratingByUserId=ratingRepository.findByUserId(userId);
		return ratingByUserId;
	}

	public List<Rating> getRatingByhotelId(Integer hotelId) {
		List<Rating> ratingByHotelId=ratingRepository.findByHotelId(hotelId);
		return ratingByHotelId;
	}

	public Rating updateRating(Integer ratingId, Rating rating) throws ResourceNotFoundException {
		Optional<Rating> getRating=ratingRepository.findById(ratingId);
		if (!getRating.isPresent()) {
			throw new ResourceNotFoundException("Rating not found for RatingId : "+ratingId);
		}
		Rating updatedRating=new Rating();
		updatedRating.setRatingid(ratingId);
		updatedRating.setUserId(rating.getUserId());
		updatedRating.setHotelId(rating.getHotelId());
		updatedRating.setRating(rating.getRating());
		updatedRating.setFeedback(rating.getFeedback());
		Rating savedRating=ratingRepository.save(updatedRating);
		return savedRating;
	}

	public void deleteRating(Integer ratingId) {
		ratingRepository.deleteById(ratingId);
		
	}

	

}
