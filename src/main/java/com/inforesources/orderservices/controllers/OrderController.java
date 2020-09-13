package com.inforesources.orderservices.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.inforesources.orderservices.model.Customer;
import com.inforesources.orderservices.model.OrderHeader;
import com.inforesources.orderservices.services.DataService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	@Autowired
	DataService ds;

	@Bean
	AmazonSQS getSQS() {
		return AmazonSQSClientBuilder.defaultClient();
	};

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderHeader> getOrder(
			@PathVariable(name = "orderId") UUID id)
			throws JsonProcessingException {
		/*
		 * Customer customer = Customer.builder().createdOn(LocalDateTime.now())
		 * .lastUpdatedOn(LocalDateTime.now())
		 * .dateOfBirth(LocalDate.parse("11/15/1975",
		 * DateTimeFormatter.ofPattern("MM/dd/yyyy")))
		 * .firstName("Karthick").lastName("Vijayasundaram")
		 * .id(UUID.randomUUID()).middleName("").build(); OrderHeader order =
		 * OrderHeader.builder().customer(customer)
		 * .createdOn(LocalDateTime.now())
		 * .lastUpdatedOn(LocalDateTime.now()).id(UUID.randomUUID())
		 * .orderTimestamp(LocalDateTime.now()).build(); return new
		 * ResponseEntity(order, HttpStatus.OK);
		 */
		OrderHeader oh = ds.getOrder(id);
		if (oh != null) {
			log.info(oh.getCreatedOn());

			// ObjectMapper om = new ObjectMapper();
			// om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// return new ResponseEntity(om.writeValueAsString(oh),
			// HttpStatus.OK);
			return new ResponseEntity(oh, HttpStatus.OK);
		} else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@GetMapping("")
	public ResponseEntity<List<OrderHeader>> getOrderByEmail(
			@RequestParam String email) {
		Customer customer = ds.getCustomerByEmailAddress(email);

		if (customer != null) {
			log.info("found customer:" + customer.getId());
			List<OrderHeader> orders = ds.getOrdersByCustomer(customer);
			return new ResponseEntity(orders, HttpStatus.OK);
		} else {
			log.info("Cant fiund customer with email:" + email);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping()
	public ResponseEntity<HttpHeaders> postOrder(
			@RequestBody OrderHeader order) {
		System.out.println("printing the order ID:" + order.getId());

		Customer customer = order.getCustomer();
		ds.saveCustomer(customer);
		UUID result = ds.saveOrder(order);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/order/" + result);

		getSQS().sendMessage(
				"https://sqs.us-east-1.amazonaws.com/555411480473/myq1",
				order.toString());
		return new ResponseEntity(headers, HttpStatus.OK);
	}

}
