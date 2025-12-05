package com.example.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @GetMapping("/inventory/stock")
    public String getStock(@RequestParam String productId) {
    	System.out.println("Stock for product " + productId + " = 203");
        return "Stock for product " + productId + " = 203";
    }
}
