package com.purchase.purchasegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PurchaseGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseGatewayApplication.class, args);
    }

}
