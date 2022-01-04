package com.example.springbootBegins.userProgress;

import com.example.springbootBegins.crop.Crop;
import com.example.springbootBegins.crop.CropService;
import com.example.springbootBegins.cropStep.CropStep;
import com.example.springbootBegins.cropStep.CropStepService;
import com.example.springbootBegins.user.Farmer;
import com.example.springbootBegins.user.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component
@Service
public class UserProgressService {

    private final UserProgressRepository userProgressRepository;
    private final FarmerService farmerService;
    private final CropStepService cropStepService;
    private final CropService cropService;

    @Autowired
    public UserProgressService(UserProgressRepository userProgressRepository,
                               FarmerService farmerService,
                               CropStepService cropStepService,
                               CropService cropService) {
        this.userProgressRepository = userProgressRepository;
        this.farmerService = farmerService;
        this.cropStepService = cropStepService;
        this.cropService = cropService;

    }

    public List<UserProgress> getUsers (){
        return userProgressRepository.findAll();
    }

    public void startGrowing(Long userId, Long cropId) {
        Farmer farmer = farmerService.getFarmerById(userId);
        CropStep cropStep = cropStepService.getCropSteps(cropId).get(0);
        System.out.println(cropStep);
        UserProgress userProgress = new UserProgress(farmer,cropStep);
        userProgressRepository.save(userProgress);
    }

    public List<UserProgress> getUserAllProgress(Long userId){
        List<UserProgress> userProgressList = userProgressRepository.findUserProgressByFarmer_Id(userId);
        return userProgressList;
    }

    public UserProgress getUserProgress(Long userId, Crop crop) {
        List<UserProgress> userProgress = userProgressRepository.findUserProgressByFarmer_IdAndCropStep_Crop(userId,crop);
        System.out.println("UG" + userProgress.get(0).toString());
        return userProgress.get(0);

    }

    @Transactional
    public void updateProgress(Long userId,
                               Long cropId) {
        Crop crop = cropService.getById(cropId);
        UserProgress userProgress = userProgressRepository.findUserProgressByFarmer_IdAndCropStep_Crop(userId,crop).get(0);

        int currentStepNo = userProgress.getCropStep().getStepNo();
        CropStep nextCropStep = cropStepService.getNextCropStep(cropId,currentStepNo);
        if (nextCropStep.getStepNo() == currentStepNo){
            // finished
            userProgress.setFinished(true);
        }else {
            // next
            userProgress.setCropStep(nextCropStep);
        }
    }

    public void deleteProgress(Long userId,Crop crop) {
        UserProgress userProgress = userProgressRepository.findUserProgressByFarmer_IdAndCropStep_Crop(userId,crop).get(0);
        this.userProgressRepository.delete(userProgress);
    }
}
