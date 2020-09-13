package com.inforesources.orderservices.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
/*
 * @NamedQueries(value = {
 *
 * @NamedQuery(name = "findBySku", query =
 * "select i from Item i where sku=?1")})
 */
@Table
public class Item {
	@Id
	private UUID id;
	@NotNull
	private String description;
	private String sku;
	@NotNull
	private LocalDateTime createdOn;
	@NotNull
	private LocalDateTime lastUpdatedOn;

}
