package com.motorepuestos.melos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MelosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelosApplication.class, args);
    }
}