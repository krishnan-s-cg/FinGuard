package com.main.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.main.dto.EmailRequest;
import com.main.dto.TransferObj;

import lombok.val;


@Service
public class NotificationService 
{
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmailId;
	
	public void sendAccountCreationEmail(EmailRequest emailRequest)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(fromEmailId);
		message.setTo(emailRequest.getToEmail());
		message.setSubject(emailRequest.getSubject());
		message.setText(emailRequest.getBody());
		
		javaMailSender.send(message);
	}
	
	/*
	 * public void transcationEmail(TransferObj obj) {
	 * 
	 * String
	 * senderMsg="Amount of "+obj.getAmount()+" successfully added to wallet " +
	 * " at "+LocalDateTime.now();
	 * 
	 * if(obj.getStatus().equals("failed")) { senderMsg+=" is failed";
	 * 
	 * } else { senderMsg +=" is success";
	 * 
	 * }
	 * 
	 * SimpleMailMessage senderMail = new SimpleMailMessage();
	 * senderMail.setFrom(fromEmailId); senderMail.setTo(obj.getSenderMail());
	 * senderMail.setText(senderMsg); senderMail.setSubject("Transcation");
	 * javaMailSender.send(senderMail);
	 * 
	 * }
	 */
}
