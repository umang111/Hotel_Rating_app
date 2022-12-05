package com.userService.app.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.userService.app.ratingFeignClient.Rating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name="user_name", length = 20)
	private String userName;
	
	@Column(name="email_id")
	private String email;
	
	@Column(name = "about_user")
	private String about;
	
	@Transient
	private List<Rating> ratings;
}
