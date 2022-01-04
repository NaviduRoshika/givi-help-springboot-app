package com.example.springbootBegins.crop;

import com.example.springbootBegins.userProgress.UserProgress;
import com.example.springbootBegins.userProgress.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

//@Component
@Service
public class CropService {

    private final CropRepository cropRepository;
    private final UserProgressRepository userProgressRepository;

    @Autowired
    public CropService(CropRepository cropRepository,UserProgressRepository userProgressRepository) {
        this.cropRepository = cropRepository;
        this.userProgressRepository = userProgressRepository;
    }

    public List<Crop> getAllCrops (){
        return cropRepository.findAll();
    }

    public Crop getById(Long id){
        return cropRepository.getById(id);
    }

    public List<Crop> getAllUserCrops(Long userId) {
        List<Crop> userCrops = new ArrayList<>();
        List<Crop> allCrops = this.cropRepository.findAll();
        List<UserProgress> userProgresses = userProgressRepository.findUserProgressByFarmer_Id(userId);
        for (UserProgress userProgress : userProgresses){
            userCrops.add(userProgress.getCropStep().getCrop());
        }
        List<Crop> availableCrops = new ArrayList<>();
        for (Crop crop : allCrops){
            if(!userCrops.contains(crop)){
                availableCrops.add(crop);
            }
        }
        return availableCrops;
    }
}
