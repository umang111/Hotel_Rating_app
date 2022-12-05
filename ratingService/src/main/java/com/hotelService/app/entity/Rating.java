package com.hotelService.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_rating")
public class Rating {

	@Id
	@GeneratedValue
	@Column(name = "rating_id")
	private int ratingid;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "hotel_id")
	private int hotelId;
	
	@Column(name = "rating")
	private int rating;
	
	@Column(name = "feedback")
	private String feedback;
}
