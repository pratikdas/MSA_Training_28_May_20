package com.training.notifier;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	

	
	public void sendEmail(EmailNotification notification) {
		log.info("Email sent {}",notification);
		notification.setFirstName(UUID.randomUUID().toString());
		notificationRepository.save(notification);
		
		
		
	}
	
	
	
	

}
