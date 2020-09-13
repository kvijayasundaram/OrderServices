package com.inforesources.orderservices.services;

import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inforesources.orderservices.model.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {

	@Query(value = "select c from Item c where c.sku = ?1")
	Item findBySku(String SKU);

}
