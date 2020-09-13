package com.inforesources.orderservices.client;

import org.joda.time.LocalDateTime;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
public class SQSTester {
	public static void main(String[] args) {
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		sqs.sendMessage("https://sqs.us-east-1.amazonaws.com/555411480473/myq1",
				"hello from java" + LocalDateTime.now());
	}
}
