package com.inforesources.orderservices.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_header")
@Builder

public class OrderHeader {
	@NotNull
	@Id
	@Column(name = "ID")
	private UUID id;

	@NotNull
	private LocalDateTime orderTimestamp;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@NotNull
	private LocalDateTime createdOn;

	@NotNull
	private LocalDateTime lastUpdatedOn;

}
