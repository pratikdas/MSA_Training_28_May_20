/**
 * 
 */
package com.training.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getUsers() {

		List<User> users = userService.getUsers();

		return users;

	}

	@PostMapping("users")
	public ResponseEntity<String> addUser(@RequestBody User user, @RequestHeader("Authorization") String authHeader) {

		userService.addUser(user);
		BodyBuilder response = ResponseEntity.status(HttpStatus.CREATED);

		return response.build();
	}

}
