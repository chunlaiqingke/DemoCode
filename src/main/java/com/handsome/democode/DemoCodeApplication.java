package com.handsome.democode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.handsome.spring")
public class DemoCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCodeApplication.class, args);
    }

}
