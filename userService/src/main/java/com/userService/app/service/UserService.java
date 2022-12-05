package com.userService.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userService.app.HotelFeignClient.Hotel;
import com.userService.app.HotelFeignClient.HotelFeignClient;
import com.userService.app.entity.User;
import com.userService.app.exceptions.ResourceNotFoundException;
import com.userService.app.ratingFeignClient.Rating;
import com.userService.app.ratingFeignClient.RatingFeignClient;
import com.userService.app.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RatingFeignClient ratingFeignClient;
	
	@Autowired
	private HotelFeignClient hotelFeignClient;
	
	public User saveUser(User user) {
		User savedUser=userRepository.save(user);
		log.info("user saved successfully");
		return savedUser;
	}
	
	public User getUserById(Integer userId) throws ResourceNotFoundException {
		
		Optional<User> user =userRepository.findById(userId);
		if(!user.isPresent()) {
			log.info("user not found with id :"+ userId);
			throw new ResourceNotFoundException("user not found "+userId);
		}
		User getUser=new User();
		getUser.setUserId(user.get().getUserId());
		getUser.setUserName(user.get().getUserName());
		getUser.setEmail(user.get().getEmail());
		getUser.setAbout(user.get().getAbout());
	    List<Rating> ratings=ratingFeignClient.getRatingByUserId(userId);
		log.info("list of rating" +ratings);
		List<Rating> ratingListWithHoteList=ratings.stream().map(rating->setHotelInRating(rating)).collect(Collectors.toList());
		getUser.setRatings(ratingListWithHoteList);
		log.info("user found successfully userId: "+ userId);
		return getUser;
	}

	private Rating setHotelInRating(Rating rating) {
		
		Hotel getHotel=hotelFeignClient.getHotelById(rating.getHotelId());
		Rating ratingWIthHotel=new Rating();
		ratingWIthHotel.setHotelId(rating.getHotelId());
		ratingWIthHotel.setRatingid(rating.getRating());
		ratingWIthHotel.setUserId(rating.getUserId());
		ratingWIthHotel.setRating(rating.getRating());
		ratingWIthHotel.setFeedback(rating.getFeedback());
		ratingWIthHotel.setHotel(getHotel);
		return ratingWIthHotel;
	}

	public List<User> getAllUser() {
		List<User> users=userRepository.findAll();
		return users;
	}

	public User updateUser(Integer userId, User user) throws ResourceNotFoundException {

		Optional<User> getUser =userRepository.findById(userId);
		if(!getUser.isPresent()) {
			throw new ResourceNotFoundException("user not found with user id: "+userId);
		}
		
		User updatedUser=new User();
		updatedUser.setUserId(userId);
		updatedUser.setUserName(user.getUserName());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setAbout(user.getAbout());
		userRepository.save(updatedUser);
		log.info("user was updated successfully ");
		return updatedUser;
	}

	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);
		log.info("user deleted successfully");
	}
	
}

















