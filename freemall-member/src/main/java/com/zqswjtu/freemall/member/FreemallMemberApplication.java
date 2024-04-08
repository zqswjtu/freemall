package com.zqswjtu.freemall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.zqswjtu.freemall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class FreemallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreemallMemberApplication.class, args);
    }

}
