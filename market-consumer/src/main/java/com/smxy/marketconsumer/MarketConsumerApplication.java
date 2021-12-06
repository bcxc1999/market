package com.smxy.marketconsumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.smxy.marketconsumer.utils")
@EnableDubboConfiguration
@SpringBootApplication
public class MarketConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketConsumerApplication.class, args);
    }

}
