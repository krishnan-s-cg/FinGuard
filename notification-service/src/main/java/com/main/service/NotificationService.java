package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.val;


@Service
public class NotificationService 
{
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmailId;
	
	public void sendEmail(String recipient)
	{
		SimpleMailMessage simpleMailMssg = new SimpleMailMessage();
		
		simpleMailMssg.setFrom(fromEmailId);
		simpleMailMssg.setTo(recipient);
		simpleMailMssg.setText("Account Created");
		javaMailSender.send(simpleMailMssg);
	}
	
	
}
