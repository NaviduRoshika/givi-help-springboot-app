package com.example.springbootBegins.userProgress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserProgressConfig {
    @Bean
    CommandLineRunner commandLineRunnerUP(UserProgressRepository userProgressRepository){
        return args -> {
        };
    }
}
