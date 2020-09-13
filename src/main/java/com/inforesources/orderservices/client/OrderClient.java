package com.inforesources.orderservices.client;

import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.inforesources.orderservices.model.OrderHeader;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@ConfigurationProperties(prefix = "order-client")
public class OrderClient {
	private String apiHost;
	private String orderServiceURI;

	private final RestTemplate rt;

	public OrderClient(RestTemplateBuilder rtb) {
		this.rt = rtb.build();
		log.info("created Rest template");
	}

	public OrderHeader getOrder() {
		UUID id = UUID.randomUUID();
		OrderHeader order = rt.getForObject(apiHost + orderServiceURI + id,
				OrderHeader.class);
		log.info("calling get order");
		return order;
	}

}
