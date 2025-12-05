package com.example.orders.service;

import com.example.orders.client.InventoryClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class InventoryService {

	private final InventoryClient inventoryClient;

	public InventoryService(InventoryClient inventoryClient) {
		this.inventoryClient = inventoryClient;
	}

	// SYNC call using Feign: CB + Retry + RateLimiter + Bulkhead
	@CircuitBreaker(name = "inventoryClient", fallbackMethod = "fallbackSync")
	@Retry(name = "inventoryClient", fallbackMethod = "fallbackSync")
	@RateLimiter(name = "inventoryClient")
	@Bulkhead(name = "inventoryClient", type = Bulkhead.Type.SEMAPHORE)
	public String getStockSync(String productId) {
		 System.out.println(">>> Calling inventoryClient for product - "+productId);
		return inventoryClient.getStock(productId);
	}

	// Fallback for sync
	public String fallbackSync(String productId, Throwable ex) {
	    System.out.println(">>> Fallback called for product " + productId +
                " due to: " + ex.getClass().getName() + " : " + ex.getMessage());
		return "Fallback stock for product " + productId + " (sync): " + ex.getClass().getSimpleName();
	}

	// ASYNC call: TimeLimiter + CircuitBreaker around Feign
	@TimeLimiter(name = "inventoryClient")
	@CircuitBreaker(name = "inventoryClient", fallbackMethod = "fallbackAsync")
	public CompletableFuture<String> getStockAsync(String productId) {
		return CompletableFuture.supplyAsync(() -> inventoryClient.getStock(productId));
	}

	// Fallback for async
	public CompletableFuture<String> fallbackAsync(String productId, Throwable ex) {
		return CompletableFuture.completedFuture(
				"Fallback stock for product " + productId + " (async): " + ex.getClass().getSimpleName());
	}
}
