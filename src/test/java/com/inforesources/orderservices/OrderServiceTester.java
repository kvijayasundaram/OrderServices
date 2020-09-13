package com.inforesources.orderservices;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inforesources.orderservices.client.OrderClient;
import com.inforesources.orderservices.model.OrderHeader;

@SpringBootTest
class OrderServiceTester {

	@Autowired
	OrderClient oc;

	@Test
	void test() {

		OrderHeader order = oc.getOrder();
		assertNotNull(order);
		System.out.println(order.toString());
	}

}
