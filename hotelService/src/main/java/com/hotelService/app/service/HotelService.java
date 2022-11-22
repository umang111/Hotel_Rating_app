package com.hotelService.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelService.app.entity.Hotel;
import com.hotelService.app.exceptions.ResourceNotFoundException;
import com.hotelService.app.repository.HotelRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	public Hotel saveHotel(Hotel hotel) {
		Hotel savedHotel=hotelRepository.save(hotel);
		log.info("Hotel saved Successfully");
		return savedHotel;
	}

	public List<Hotel> getHotelsList() {
		List<Hotel> listOfHotels=hotelRepository.findAll();
		return listOfHotels;
	}

	public Hotel getHotelById(Integer hotelId) throws ResourceNotFoundException {
		Optional<Hotel> getHotel = hotelRepository.findById(hotelId);
		if (!getHotel.isPresent()) {
			log.info("Hotel not found with hotelId : "+hotelId);
			throw new ResourceNotFoundException("Hotel not found with HotelId: "+hotelId);
		}
		Hotel hotelget=new Hotel();
		hotelget.setHotelId(getHotel.get().getHotelId());
		hotelget.setName(getHotel.get().getName());
		hotelget.setLocation(getHotel.get().getLocation());
		hotelget.setAbout(getHotel.get().getAbout());
		
		return hotelget;
	}

	public Hotel udateHotel(Integer hotelId, Hotel hotel) throws ResourceNotFoundException {
		Optional<Hotel> getHotel = hotelRepository.findById(hotelId);
		if (!getHotel.isPresent()) {
			log.info("Hotel not found with hotelId : "+hotelId);
			throw new ResourceNotFoundException("Hotel not found with HotelId: "+hotelId);
		}
		Hotel updateHotel=new Hotel();
		updateHotel.setHotelId(hotelId);
		updateHotel.setName(hotel.getName());
		updateHotel.setLocation(hotel.getLocation());
		updateHotel.setAbout(hotel.getAbout());
		
		log.info("Hotel updated Successfully");
		return updateHotel;
	}
	
	public void delteHotelById(Integer hotelId) {
		hotelRepository.deleteById(hotelId);
	}
	
}
