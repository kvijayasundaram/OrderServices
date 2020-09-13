package com.inforesources.orderservices.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQueries(value = {
		@NamedQuery(name = "findByEmailAddress", query = "select c from Customer c where c.emailAddress = ?1")})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CUSTOMER")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {
	@NotNull
	@Id
	@Column(name = "ID")
	private UUID id;

	@NotNull
	@Size(min = 1, max = 100)
	private String firstName;
	@NotNull

	@Size(min = 1, max = 100)
	private String lastName;
	private String middleName;

	@NotNull
	private LocalDate dateOfBirth;

	@OneToMany(mappedBy = "customer")
	private List<CustomerAddress> customerAddresses;

	@OneToMany(mappedBy = "customer")
	private List<OrderHeader> orders;

	@Email
	private String emailAddress;

	@NotNull
	private LocalDateTime createdOn;

	@NotNull
	private LocalDateTime lastUpdatedOn;

}
