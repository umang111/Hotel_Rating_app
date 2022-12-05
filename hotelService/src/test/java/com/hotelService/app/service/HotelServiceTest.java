package com.hotelService.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hotelService.app.entity.Hotel;
import com.hotelService.app.exceptions.ResourceNotFoundException;
import com.hotelService.app.repository.HotelRepository;

@SpringBootTest
class HotelServiceTest {

	@MockBean
	private HotelRepository hotelRepository;
	
	@Autowired
	private HotelService hotelService;
	
	private Hotel hotel;
	
	@BeforeEach
	private void setup() {
		hotel=getHotel();}
	
	@Test
	public void test_saveHotel() {
		when(hotelService.saveHotel(hotel)).thenReturn(hotel);
		Hotel savedHotel=hotelService.saveHotel(hotel);
		assertEquals(1, savedHotel.getHotelId());
	}
	
	@Test
	public void test_getHotelsList() {
		List<Hotel> hotelList =new ArrayList<>();
		hotelList.add(hotel);
		hotelList.add(hotel);
		when(hotelRepository.findAll()).thenReturn(hotelList);
		hotelService.getHotelsList();
	}
	
	@Test
	public void test_getHotelById() {
		when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
		try {
			Hotel getHotel=hotelService.getHotelById(1);
		} catch (ResourceNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test
	public void test_updateHotel() throws ResourceNotFoundException {
		when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
		hotelService.udateHotel(1, hotel);
		
	}
	
	@Test
	public void test_deleteHotel() {
		hotelService.delteHotelById(1);
		verify(hotelRepository,times(1)).deleteById(1);
	}
	
	
	private Hotel getHotel() {
	Hotel hotel =new Hotel();
	hotel.setHotelId(1);
	hotel.setName("Taj Hotel");
	hotel.setLocation("Delhi");
	hotel.setAbout("Taj hotel is a good Hotel");
	return hotel;
}

}
