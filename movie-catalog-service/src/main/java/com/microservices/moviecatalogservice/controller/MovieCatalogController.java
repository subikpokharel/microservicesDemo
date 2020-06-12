package com.microservices.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviecatalogservice.model.CatalogItem;
import com.microservices.moviecatalogservice.model.Movie;
import com.microservices.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(path = "/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {

		UserRating ratings = restTemplate.getForObject("http://localhost:8085/rating/users/" + userId,
				UserRating.class);

		return ratings.getUserRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8087/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());
	}

	/*
	 * // Using WebClient (async) instead of rest template
	 * 
	 * @GetMapping(path = "/{userId}") public List<CatalogItem>
	 * getCatalogReactive(@PathVariable String userId) {
	 * 
	 * WebClient.Builder builder = WebClient.builder();
	 * 
	 * List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new
	 * Rating("5678", 3));
	 * 
	 * return ratings.stream().map(rating -> { Movie movie = builder.build() //Web
	 * client initialization .get() //Request headers Uri specific
	 * .uri("http://localhost:8087/movies/" + rating.getMovieId()) //url where the
	 * ms is hosted .retrieve() //response specific .bodyToMono(Movie.class) //Async
	 * call where mono is gonna wait till it receives the object Mono<Movie>
	 * .block(); //block execution till we have a result return new
	 * CatalogItem(movie.getName(), "Test", rating.getRating());
	 * }).collect(Collectors.toList()); }
	 */

}
