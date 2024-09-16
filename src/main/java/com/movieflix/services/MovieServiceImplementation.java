package com.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entities.Movie;
import com.movieflix.entities.User;
import com.movieflix.repositories.MovieRepository;


@Service
public class MovieServiceImplementation implements MovieService{

	
	@Autowired
	MovieRepository mr;
	
	@Override
	public String addMovie(Movie mov) {
		mr.save(mov);
		return " movie is added";
		
	}
	

	@Override
	public List<Movie> viewmovie() {
		List<Movie> movieList=mr.findAll();
		return movieList;
	}

}
