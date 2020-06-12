package com.microservices.ratingdataservice.model;

import java.util.List;

public class UserRating {

	List<Rating> userRatings;

	public UserRating(List<Rating> userRatings) {
		super();
		this.userRatings = userRatings;
	}

	public UserRating() {
		// TODO Auto-generated constructor stub
	}

	public List<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(List<Rating> userRatings) {
		this.userRatings = userRatings;
	}

}
