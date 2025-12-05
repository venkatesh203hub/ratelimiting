package com.example.deliverys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DeliverysServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliverysServiceApplication.class, args);
    }
}
