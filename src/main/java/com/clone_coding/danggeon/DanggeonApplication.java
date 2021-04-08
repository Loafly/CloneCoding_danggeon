package com.clone_coding.danggeon;

import com.clone_coding.danggeon.bcrypt.BcryptImpl;
import com.clone_coding.danggeon.bcrypt.EncryptHelper;
import com.clone_coding.danggeon.utils.GetBoards;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DanggeonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanggeonApplication.class, args);
    }
    @Bean
    public EncryptHelper encryptConfig() {
        return new BcryptImpl();
    }

}



