package com.test.bloggingplatformapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BloggingPlatformApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingPlatformApiApplication.class, args);
    }

}
