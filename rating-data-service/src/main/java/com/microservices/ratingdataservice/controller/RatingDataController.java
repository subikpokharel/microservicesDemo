package com.microservices.ratingdataservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.ratingdataservice.model.Rating;
import com.microservices.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingDataController {

	@GetMapping(path = "/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId, 4);
	}
	
	@GetMapping(path = "users/{userId}")
	public UserRating getUserRating(@PathVariable String userId) {
		List<Rating> ratings = Arrays.asList(
					new Rating("1234", 4),
					new Rating("5678", 3)
				);
		UserRating userRating = new UserRating();
		userRating.setUserRatings(ratings);
		return userRating;
	}
}
