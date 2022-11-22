package com.userService.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
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
	private List<Rating> ratings=new ArrayList<>();
}
