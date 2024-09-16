package com.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movieflix.entities.Movie;
import com.movieflix.entities.User;
import com.movieflix.repositories.UserRepository;
import com.movieflix.services.MovieService;
import com.movieflix.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService userv;
	
	@Autowired
	MovieService movserv;
	
	@Autowired
	UserRepository ur;
	


	
	
	@PostMapping("register")
	public String addUser(@ModelAttribute User usr) {
		
		boolean status = userv.emailExists(usr.getEmail());
		if(status==true)
		{
			return "regfail";
		}
		else
		{
			userv.addUser(usr);
			return"registersuccess";
		}
		
	}
	
	@PostMapping("login")
	public String loginuser(@RequestParam String email, @RequestParam String password , HttpSession session)
	{
		boolean status=userv.emailExists(email);
			if(!status)
			{
				return"loginfail";
			}
		
		
		boolean loginStatus=userv.loginUser(email, password);
		if(loginStatus==true)
		{
			session.setAttribute("email",email);
			
			if(email.equals("admin@gmail.com"))
			{
				return "admin/adminhome";
		   }
			else
			{
				return "home";
			}
		}
	
		else{
			return "login";
		}
 }
	
	
	@GetMapping("viewuser")
	public String viewUser(Model model)
	{
		List<User> userList=userv.viewUser();
		model.addAttribute("user", userList);
		return "admin/viewuser";
	}
	

	@GetMapping("exploremovies")
	public String exploreMovie(Model model,HttpSession session)
	{
	String email =  (String)session.getAttribute("email");
	 User urr =   userv.getUser(email);
	 if(urr.isPremium()==true)
	 {
		 //Getting LIst of Movies
		 List<Movie> movieList=movserv.viewmovie();
			//adding the details in the model
		 model.addAttribute("movie", movieList);
		 
		 return "viewmoviesuser";
	 }
	 else {
		 
		 return "payment";
		 
	 }
	
	
	 
}
	
	
	@GetMapping("update-p")
    public String editUser(Model model,HttpSession session) {
	 String email=(String)session.getAttribute("email");
        User user = ur.findByEmail(email);
        model.addAttribute("user", user);
        return "edit-form"; // Your HTML form template
    }



@PostMapping("update-register")
public String updUs(Model model,@RequestParam int id, @Validated @ModelAttribute User user, BindingResult result)
{
	User usr=ur.findById(id).get();
	model.addAttribute("usr", usr);
	usr.setName(user.getName());
	usr.setEmail(user.getEmail());
	usr.setGender(user.getGender());
	usr.setPassword(user.getPassword());
	usr.setAddress(user.getAddress());
	usr.setPhone(user.getPhone());
	ur.save(usr);
	return "home";
}
	
	

	@GetMapping("logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
	
	
	
    
 	
	
	
	
	
}
	


