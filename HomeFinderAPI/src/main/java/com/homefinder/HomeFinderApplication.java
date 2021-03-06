package com.homefinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@SpringBootApplication
@EnableAsync
public class HomeFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeFinderApplication.class,args);
    }
}