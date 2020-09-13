package com.inforesources.orderservices.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforesources.orderservices.model.Customer;
import com.inforesources.orderservices.model.Item;
import com.inforesources.orderservices.model.OrderHeader;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DataService {

	@Autowired
	OrderRepository or;

	@Autowired
	CustomerRepository cr;

	@Autowired
	ItemRepository ir;

	public OrderHeader getOrder(UUID id) {
		return or.getOne(id);
	}

	public UUID saveOrder(OrderHeader order) {
		log.info("printing the order ID:" + order.getId());
		OrderHeader order2 = or.save(order);
		log.info("saving the order ID:" + order.getId());
		log.info(" saved order :" + order);
		return order2.getId();
	}

	public UUID saveCustomer(Customer customer) {
		log.info("printing the customer ID:" + customer.getId());
		Customer customer2 = cr.save(customer);
		log.info("saving the customer ID:" + customer.getId());
		log.info(" saved customer :" + customer);
		return customer2.getId();
	}

	public Customer getCustomerByEmailAddress(String emailAddress) {
		return cr.findByEmailAddress(emailAddress);
	}

	public List<OrderHeader> getOrdersByCustomer(Customer customer) {
		return or.findAllByCustomer(customer);
	}

	public UUID saveItem(Item item) {
		log.info("saving item");
		ir.save(item);
		return item.getId();
	}
}
