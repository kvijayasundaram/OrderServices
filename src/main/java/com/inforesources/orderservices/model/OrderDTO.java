package com.inforesources.orderservices.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {

	private UUID id;

	private LocalDateTime orderTimestamp;

	private Customer customer;

	private LocalDateTime createdOn;

	private LocalDateTime lastUpdatedOn;

}
