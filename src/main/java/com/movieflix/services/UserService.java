package com.movieflix.services;


import java.util.List;

import com.movieflix.entities.User;

public interface UserService {
	
	public String addUser(User u);
	
	public boolean emailExists(String email);
	
	public boolean loginUser(String email, String password);
	
	public List<User> viewUser();
	public User getUser(String email);
     public String updateUser(User user );

	

}
