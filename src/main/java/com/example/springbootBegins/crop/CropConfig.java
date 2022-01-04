package com.example.springbootBegins.crop;

import com.example.springbootBegins.cropStep.CropStep;
import com.example.springbootBegins.cropStep.CropStepRepository;
import com.example.springbootBegins.user.Farmer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class CropConfig {
    @Bean
    CommandLineRunner commandLineRunnerA(CropRepository cropRepository, CropStepRepository cropStepRepository){
        return args -> {

            List<Crop> crops = cropRepository.findAll();
            if (crops.isEmpty()){
                Crop rice = new Crop("Rice", "Rice is the seed of the grass species Oryza sativa or less commonly Oryza glaberrima.");
                Crop banana = new Crop("Banana","A banana is an elongated, edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa");
                Crop coconut =  new Crop("Coconut","Coconut is the fruit of the coconut palm (Cocos nucifera). It's used for its water, milk, oil, and tasty meat.");

                cropRepository.saveAll(List.of(rice,banana,coconut));

                Optional<Crop> rice2 = cropRepository.findCropByName(rice.getName());
                if (rice2.isPresent()){
                    cropStepRepository.saveAll(List.of(
                            new CropStep(1,"Seed selection.",rice2.get()),
                            new CropStep(2,"Land preparation.",rice2.get()),
                            new CropStep(3,"Crop establishment.",rice2.get()),
                            new CropStep(4,"Water management.",rice2.get()),
                            new CropStep(5,"Nutrient management.",rice2.get()),
                            new CropStep(6,"Crop health management.",rice2.get()),
                            new CropStep(7,"Harvesting.",rice2.get()),
                            new CropStep(8,"Post harvest.",rice2.get())
                    ));
                }

                Optional<Crop> banana2 = cropRepository.findCropByName(banana.getName());
                if (banana2.isPresent()){
                    cropStepRepository.saveAll(List.of(
                            new CropStep(1,"Look up your area's temperature and humidity",banana2.get()),
                            new CropStep(2,"Find the sunniest area in your yard",banana2.get()),
                            new CropStep(3,"Choose an area with good drainage.",banana2.get()),
                            new CropStep(4,"Allow sufficient space.",banana2.get()),
                            new CropStep(5,"Consider growing it indoors.",banana2.get()),
                            new CropStep(6,"Caring For Your Plant",banana2.get()),
                            new CropStep(7,"Water frequently but avoid overwatering.",banana2.get()),
                            new CropStep(8,"Nurturing and Harvesting Fruit.",banana2.get())
                    ));
                }

                Optional<Crop> coconut2 = cropRepository.findCropByName(coconut.getName());
                if (coconut2.isPresent()){
                    cropStepRepository.saveAll(List.of(
                            new CropStep(1,"Germinating Your Coconut Seed",coconut2.get()),
                            new CropStep(2,"Planting Your Tree",coconut2.get()),
                            new CropStep(3,"Place the coconut in a bucket of lukewarm water.",coconut2.get()),
                            new CropStep(4,"Fill a zip-lock plastic bag with 1 cup (240 mL) of water.",coconut2.get()),
                            new CropStep(5,"Check on the coconut every week for germination. ",coconut2.get()),
                            new CropStep(6,"Caring For Your Plant",coconut2.get()),
                            new CropStep(7,"Give your coconut tree plenty of water and sunlight.",coconut2.get()),
                            new CropStep(8,"Harvesting Fruit.",coconut2.get())
                    ));
                }
            }
        };
    }
}
