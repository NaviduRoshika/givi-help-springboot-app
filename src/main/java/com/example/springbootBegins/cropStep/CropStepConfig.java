package com.example.springbootBegins.cropStep;

import com.example.springbootBegins.crop.CropRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CropStepConfig {
    @Bean
    CommandLineRunner commandLineRunnerCropStep(CropStepRepository cropStepRepository){
        return args -> {

        };
    }
}
