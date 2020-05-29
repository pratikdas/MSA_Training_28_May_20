package com.training.notifier;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="NOTIFICATIONS")
@Entity
public class EmailNotification {
	
	@Id
	private String firstName;
	private String messageText;
	private String email;

}
