package com.training.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailNotification {
	private String firstName;
	private String messageText;
	private String email;

}
