package com.inforesources.orderservices.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
@Builder
public class Address {
	@NotNull
	@Id
	@Column(name = "ID")
	private UUID id;

	@NotNull
	private String streetNumber;

	@NotNull
	@Size(min = 1, max = 100)
	private String streetName;

	private String streetsuffix;
	private String additionalLine;
	@NotNull
	@Size(min = 1, max = 100)
	private String city;

	private String division;
	@NotNull
	private String province;

	@NotNull
	@Size(min = 1, max = 100)
	private String country;

	@NotNull
	private String postalCode;

	private String postalCodeExtension;
	private long latitude;
	private long longitude;

	@OneToMany(mappedBy = "address")
	private List<CustomerAddress> customerAddresses;

	@NotNull
	private LocalDateTime createdOn;

	@NotNull
	private LocalDateTime lastUpdatedOn;

}
