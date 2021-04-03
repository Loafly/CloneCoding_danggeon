package com.clone_coding.danggeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DanggeonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanggeonApplication.class, args);
    }

}
