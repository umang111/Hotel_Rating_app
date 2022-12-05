package com.hotelService.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelService.app.entity.Hotel;

public interface HotelRepository  extends JpaRepository<Hotel, Integer>{

}
