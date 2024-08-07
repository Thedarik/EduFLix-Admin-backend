package com.eduflix.eduflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.eduflix.eduflix")
public class EduflixApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduflixApplication.class, args);
    }

}
