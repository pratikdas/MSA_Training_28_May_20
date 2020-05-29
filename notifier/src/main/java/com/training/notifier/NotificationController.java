/**
 * 
 */
package com.training.notifier;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	

	@PostMapping("notify/email/send")
	public ResponseEntity<Map<String, String>> addUser(@RequestBody EmailNotification notification) {

		notificationService.sendEmail(notification);;
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "ok");
		return  ResponseEntity.ok(result);

		
	}

}
