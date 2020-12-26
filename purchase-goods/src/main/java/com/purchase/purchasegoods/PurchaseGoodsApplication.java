package com.purchase.purchasegoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PurchaseGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseGoodsApplication.class, args);
    }

//    @Bean
//    public Snowflake snowflake() {
//        return IdUtil.getSnowflake(1, 1);
//    }
}
