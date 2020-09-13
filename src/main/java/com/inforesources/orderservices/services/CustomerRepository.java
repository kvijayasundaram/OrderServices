package com.inforesources.orderservices.services;

import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inforesources.orderservices.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
	@Query("select c from Customer c where c.emailAddress = ?1")
	Customer findByEmailAddress(String emailAddress);
}
