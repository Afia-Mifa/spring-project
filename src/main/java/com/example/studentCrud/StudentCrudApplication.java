package com.example.studentCrud;

import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

@SpringBootApplication
public class StudentCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentCrudApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
