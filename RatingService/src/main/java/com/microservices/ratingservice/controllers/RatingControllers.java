package com.microservices.ratingservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.ratingservice.entities.Rating;
import com.microservices.ratingservice.services.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingControllers {
	@Autowired
	private RatingService ratingService;
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating() {
		List<Rating> allRatings = ratingService.getAllRating();
		return ResponseEntity.status(HttpStatus.FOUND).body(allRatings);
	}

	@PostMapping("/add")
	public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
		Rating currrating = ratingService.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(currrating);
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userid") String userid) {
		List<Rating> allRatings = ratingService.getRatingByUserId(userid);
		return ResponseEntity.status(HttpStatus.FOUND).body(allRatings);
	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("hotelId") String hotelId) {
		List<Rating> allRatings = ratingService.getRatingByHotelId(hotelId);
		return ResponseEntity.status(HttpStatus.FOUND).body(allRatings);
	}
}
