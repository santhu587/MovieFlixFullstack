package com.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.movieflix.entities.Movie;
import com.movieflix.entities.User;
import com.movieflix.services.MovieService;

@Controller
public class MovieController {
	@Autowired
	MovieService ms;
	
	@PostMapping("addmoviecont")
	public String addMovie(@ModelAttribute Movie mov ) {
		ms.addMovie(mov);
		return "admin/addmoviesuccess";
	}
	

	@GetMapping("viewmovie")
	public String viewMovie(Model model)
	{
		List<Movie> movieList=ms.viewmovie();
		model.addAttribute("movie", movieList);
		return "admin/viewmovies";
	}
	
	
	@GetMapping("viewmovieuser")
	public String viewMovieUser(Model model)
	{
		List<Movie> movieList=ms.viewmovie();
		model.addAttribute("movie", movieList);
		return "viewmoviesuser";
	}
	

}
