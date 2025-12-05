package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServerApplication.class, args);
    }
}

