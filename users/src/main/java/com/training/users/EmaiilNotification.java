package com.training.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmaiilNotification {
	
	private String messageText;
	private String email;

}
