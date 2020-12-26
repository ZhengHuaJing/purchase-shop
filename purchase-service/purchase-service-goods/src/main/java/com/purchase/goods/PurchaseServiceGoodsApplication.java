package com.purchase.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PurchaseServiceGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseServiceGoodsApplication.class, args);
    }

}
