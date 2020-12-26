package com.purchase.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.purchase.user.feign")
public class PurchaseAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseAuthApplication.class, args);
    }

}
