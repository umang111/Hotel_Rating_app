package com.hotelService.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

	@Id
	@GeneratedValue
	@Column(name = "hotel_id")
	private int hotelId;
	
	@Column(name = "hotel_name")
	private String name;
	
	@Column(name = "hotel_location")
	private String location;
	
	@Column(name = "about")
	private String about;
}
