package com.microservices.ratingservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.ratingservice.entities.Rating;
import com.microservices.ratingservice.repository.RatingRepository;
import com.microservices.ratingservice.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	@Autowired
	private RatingRepository ratingRepository;
	@Override
	public Rating createRating(Rating rating) {
		String id = UUID.randomUUID().toString();
		rating.setRatingId(id);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
