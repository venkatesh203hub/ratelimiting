package com.example.deliverys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
public class DeliveryController {

    @GetMapping("/deliverys")
    public String hello() {
    	System.out.println("deliverys service instance: " + UUID.randomUUID() +"deliverd successfully"+
               " at " + Instant.now());
        return "deliverys service instance: " + UUID.randomUUID() +"deliverd successfully"+
                " at " + Instant.now();
    }
}
