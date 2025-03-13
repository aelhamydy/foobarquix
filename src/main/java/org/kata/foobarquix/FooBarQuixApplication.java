package org.kata.foobarquix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FooBarQuixApplication {
    public static void main(String[] args) {
        SpringApplication.run(FooBarQuixApplication.class, args);
    }
}
