package com.example.springbootBegins.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FarmerConfig {
    @Bean
    CommandLineRunner UserCommandLineRunner(FarmerRepository farmerRepository){
        return args -> {
            List<Farmer> farmers = farmerRepository.findAll();
            if (farmers.isEmpty()){
                Farmer sam = new Farmer("Sam","sam@gmail.com","sam123");
                Farmer alex = new Farmer(
                        "Alex",
                        "alex@gmail.com",
                        "alex123");

                farmerRepository.saveAll(
                        List.of(sam,alex)
                );
            }
        };
    }
}
