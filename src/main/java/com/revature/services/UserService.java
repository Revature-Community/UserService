package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	// use to get all the users from db
	public List<User> findAll() {

		List<User> users = userRepo.findAll();

		return users;
	}

	// use to add a new user to the db
	public User save(User user) {
		User u = userRepo.save(user);
		return u;

	}

	// use to remove a user from the db
//	public Map<String, Boolean> remove(String email) throws ResourceNotFoundException {
//
//		User userToDel = userRepo.findOne(Example.of(new User(email, "", "", "", "", null, null)))
//				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//		userRepo.delete(userToDel);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//
//		return response;
//	}

	public User findOne(Long id) throws ResourceNotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	public User login(User user) throws ResourceNotFoundException {
		Example<User> userEx = Example.of(user);
		User userLoggedIn = userRepo.findOne(userEx).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return userLoggedIn;
	}



}
