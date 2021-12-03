package com.smxy.marketconsumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfiguration
@SpringBootApplication
public class MarketConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketConsumerApplication.class, args);
    }

}
