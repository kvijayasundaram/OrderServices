package com.inforesources.orderservices.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inforesources.orderservices.model.Customer;
import com.inforesources.orderservices.model.OrderHeader;

@Repository
public interface OrderRepository extends JpaRepository<OrderHeader, UUID> {

	@Query("select o from OrderHeader o where o.customer = ?1")
	public List<OrderHeader> findAllByCustomer(Customer c);
}
