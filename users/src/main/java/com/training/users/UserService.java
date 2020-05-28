package com.training.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private NotiifierIntegrator notiifierIntegrator;

	
	public void addUser(User user) {
		userRepository.save(user);
		
		
		EmaiilNotification emaiilNotification = EmaiilNotification.builder().build();;
		// TODO invoke notifier API
		notiifierIntegrator.sendEmail(emaiilNotification );
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	

}
