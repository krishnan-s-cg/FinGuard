package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.EmailRequest;
import com.main.dto.WalletUpdateEmailRequest;
//import com.main.dto.TransferObj;
import com.main.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController 
{
	@Autowired
	NotificationService notificationservice;
	
	// Endpoint to send account creation email
    @PostMapping("/sendEmail")
    public ResponseEntity<Void> sendAccountCreationEmail(@RequestBody EmailRequest emailRequest) {
        // Call the service method to send the email
        notificationservice.sendAccountCreationEmail(emailRequest);
        return new ResponseEntity<>(HttpStatus.OK); // Return success status
    }
	
    @PostMapping("/sendWalletUpdateEmail")
    public ResponseEntity<Void> sendWalletUpdateEmail(@RequestBody WalletUpdateEmailRequest walletUpdateRequest)
    {
    	notificationservice.sendWalletUpdateEmail( walletUpdateRequest);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
	
	/*
	 * @PostMapping("/send-msg/send") public String transcationMsg(@RequestBody
	 * TransferObj obj) { notificationservice.transcationEmail(obj); return
	 * "Email Sent successfully"; }
	 */
}
