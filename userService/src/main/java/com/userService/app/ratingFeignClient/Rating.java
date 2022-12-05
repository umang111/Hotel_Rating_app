package com.userService.app.ratingFeignClient;

import com.userService.app.HotelFeignClient.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

	private int ratingid;
	private int userId;
	private int hotelId;
	private int rating;
	private String feedback;
	
	private Hotel hotel;
}
