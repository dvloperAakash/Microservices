package com.microservices.hms.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.hms.entities.Hotel;
import com.microservices.hms.entities.Rating;
import com.microservices.hms.entities.User;
import com.microservices.hms.repositories.UserRepository;
import com.microservices.hms.services.UserService;
import com.microservices.hms.services.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id" + userId + "does not exists"));
		// fetch rating service of above user from RATING SERVICE
		Rating[] ratingsOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(),Rating[].class);
		List<Rating>ratings = Arrays.stream(ratingsOfUser).toList();
		logger.info("{}", ratingsOfUser);
		
		List<Rating>ratingList = ratings.stream().map(rating->{
			ResponseEntity<Hotel>forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = forEntity.getBody();
			logger.info("response status code: {}",forEntity.getStatusCode());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
