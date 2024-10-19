package com.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequest {
	private int senderUserId;
	private int receiverUserId;
	private double amount;
}
