package com.hotelService.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
