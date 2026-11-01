package com.herbert.wanderbyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WanderByWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WanderByWayApplication.class, args);
    }

}
