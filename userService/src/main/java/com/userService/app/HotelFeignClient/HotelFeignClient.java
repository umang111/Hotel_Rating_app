package com.userService.app.HotelFeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "hotel-service", url = "http://localhost:8082/hotel")
public interface HotelFeignClient {

	@GetMapping("/findById/{hotelId}")
	public Hotel getHotelById(@PathVariable("hotelId") Integer hotelId);
}
