package com.main.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.main.dto.EmailRequest;

@FeignClient(name = "notification-service")
public interface NotificationClient {
	
	@PostMapping("/notification/sendEmail")
	ResponseEntity<Void> sendAccountCreationEmail(@RequestBody EmailRequest emailRequest);

}
