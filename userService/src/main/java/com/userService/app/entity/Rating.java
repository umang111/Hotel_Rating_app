package com.userService.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

	private String ratingid;
	private String userid;
	private String hotelId;
	private int rating;
	private String feedback;
}
