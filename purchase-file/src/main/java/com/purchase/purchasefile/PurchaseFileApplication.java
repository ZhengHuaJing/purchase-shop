package com.purchase.purchasefile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PurchaseFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseFileApplication.class, args);
    }

}
