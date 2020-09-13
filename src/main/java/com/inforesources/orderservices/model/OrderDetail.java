package com.inforesources.orderservices.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class OrderDetail {
	@Id
	private UUID id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private OrderHeader order;

	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	private int quantity;
	private double cost;
	@NotNull
	private LocalDateTime createdOn;

	@NotNull
	private LocalDateTime lastUpdatedOn;
}
