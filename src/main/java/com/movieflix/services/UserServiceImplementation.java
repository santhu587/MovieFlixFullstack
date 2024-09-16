package com.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entities.User;
import com.movieflix.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	
	@Autowired
	UserRepository ur;
	
	
	@Override
	public String addUser(User u) {
		ur.save(u);
		return "User created";
		
	}

	@Override
	public boolean emailExists(String email) {
		if(ur.findByEmail(email)==null)
		{
			return false;
			
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean loginUser(String email, String password) {
		
		User u=ur.findByEmail(email);
		String pas=u.getPassword();
		if(pas.equals(password))
			
				{
			      return true;
				}
		else
		{
			return false;
		}		
		
	}

	@Override
	public List<User> viewUser() {
		List<User> userList =ur.findAll();
		return userList;
	}

	@Override
	public User getUser(String email) {
		User user = ur.findByEmail(email);
		return user;
	}

	@Override
	public String updateUser(User user) {
		ur.save(user);
		return "user updated";
	}

	


}
