package com.example.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// inventory-service should be a Eureka-registered service
@FeignClient(name = "inventory-service", path = "/inventory")
public interface InventoryClient {

	@GetMapping("/stock")
	String getStock(@RequestParam("productId") String productId);
}
