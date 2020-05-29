package com.training.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private NotifyIntegrator notifyIntegrator;

	
	public void addUser(User user) {
		userRepository.save(user);
		
		String messageText = "User is created";
		
		notifyIntegrator.sendEmail(user.getFirstName(), messageText , user.getEmail() );
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	

}
