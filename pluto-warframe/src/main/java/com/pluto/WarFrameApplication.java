package com.pluto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.pluto.mapper")
@EnableDiscoveryClient
public class WarFrameApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarFrameApplication.class, args);
    }
}
