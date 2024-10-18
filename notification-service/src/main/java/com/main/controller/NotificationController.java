package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.TransferObj;
import com.main.service.NotificationService;

@RestController
public class NotificationController 
{
	@Autowired
	NotificationService notificationservice;
	
	@PostMapping("/send-msg/send")
	public String transcationMsg(@RequestBody TransferObj obj) {
		notificationservice.transcationEmail(obj);
		return "Email Sent successfully";
	}
}
