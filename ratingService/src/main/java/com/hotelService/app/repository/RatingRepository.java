package com.hotelService.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelService.app.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	List<Rating> findByUserId(Integer userId);

	List<Rating> findByHotelId(Integer hotelId);

}
