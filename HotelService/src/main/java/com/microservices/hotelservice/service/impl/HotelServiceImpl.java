package com.microservices.hotelservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.hotelservice.entities.Hotel;
import com.microservices.hotelservice.exceptions.ResourceNotFoundException;
import com.microservices.hotelservice.repositories.HotelRepository;
import com.microservices.hotelservice.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {
		String id = UUID.randomUUID().toString();
		hotel.setId(id);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getSingleHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("hotel with given id does not exist"));
	}

}
