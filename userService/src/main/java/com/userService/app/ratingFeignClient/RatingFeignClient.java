package com.userService.app.ratingFeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "rating-service", url = "http://localhost:8083/rating")
public interface RatingFeignClient {

	@GetMapping("/user/{userId}")
	public List<Rating> getRatingByUserId(@PathVariable("userId") Integer userId);
}
