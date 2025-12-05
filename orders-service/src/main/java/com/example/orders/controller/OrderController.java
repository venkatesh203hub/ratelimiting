package com.example.orders.controller;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.orders.service.InventoryService;

@RestController
public class OrderController {

	private final InventoryService inventoryService;

	public OrderController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/orders")
	public String hello() {
		System.out.println("Orders service instance: " + UUID.randomUUID() + " at " + Instant.now());
		return "Orders service instance: " + UUID.randomUUID() + " at " + Instant.now();
	}

	@GetMapping("/orders/check-stock-sync")
	public String checkStockSync(@RequestParam String productId) {
		return inventoryService.getStockSync(productId);
	}

	@GetMapping("/orders/check-stock-async")
	public CompletableFuture<String> checkStockAsync(@RequestParam String productId) {
		return inventoryService.getStockAsync(productId);
	}

}
