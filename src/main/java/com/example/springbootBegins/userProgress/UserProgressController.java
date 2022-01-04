package com.example.springbootBegins.userProgress;

import com.example.springbootBegins.crop.Crop;
import com.example.springbootBegins.crop.CropService;
import com.example.springbootBegins.cropStep.CropStep;
import com.example.springbootBegins.cropStep.CropStepService;
import com.example.springbootBegins.user.Farmer;
import com.example.springbootBegins.user.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(path = "userprogress")
public class UserProgressController {

    private final UserProgressService userProgressService;
    private final CropService cropService;
    private  final  CropStepService cropStepService;
    private final FarmerService farmerService;

    @Autowired
    public UserProgressController(UserProgressService userProgressService,
                                  CropService cropService,
                                  CropStepService cropStepService,
                                  FarmerService farmerService) {
        this.userProgressService = userProgressService;
        this.cropService = cropService;
        this.cropStepService = cropStepService;
        this.farmerService = farmerService;
    }

    @GetMapping
    public List<UserProgress> getUserProgress (){

        return  this.userProgressService.getUsers();
    }

    @GetMapping(path = "start/{userId}/{cropId}")
    public String startGrowing(@PathVariable("userId") Long userId, @PathVariable("cropId") Long cropId, Model model){

        this.userProgressService.startGrowing(userId,cropId);

        Farmer farmer = farmerService.getFarmerById(userId);
        model.addAttribute("user",farmer);
        model.addAttribute("crop",cropService.getById(cropId));
        model.addAttribute("steps",cropStepService.getCropSteps(cropId));
        UserProgress userProgress = userProgressService.getUserProgress(userId,cropService.getById(cropId));
        CropStep currentCropStep = userProgress.getCropStep();
        model.addAttribute("currentStep",currentCropStep);
        return "growing";
    }

    @GetMapping(path = "view/{userId}/{cropId}")
    public String viewGrowing(@PathVariable("userId") Long userId, @PathVariable("cropId") Long cropId, Model model){
        Farmer farmer = farmerService.getFarmerById(userId);
        model.addAttribute("user",farmer);
        model.addAttribute("crop",cropService.getById(cropId));
        model.addAttribute("steps",cropStepService.getCropSteps(cropId));
        UserProgress userProgress = userProgressService.getUserProgress(userId,cropService.getById(cropId));
        CropStep currentCropStep = userProgress.getCropStep();
        model.addAttribute("currentStep",currentCropStep);
        return "growing";
    }

    @GetMapping(path = "stepdone/{userId}/{cropId}")
    public String stepDone(@PathVariable("userId") Long userId, @PathVariable("cropId") Long cropId, Model model){

        this.userProgressService.updateProgress(userId,cropId);

        Farmer farmer = farmerService.getFarmerById(userId);
        model.addAttribute("user",farmer);

        model.addAttribute("crop",cropService.getById(cropId));
        model.addAttribute("steps",cropStepService.getCropSteps(cropId));

        UserProgress userProgress = userProgressService.getUserProgress(userId,cropService.getById(cropId));
        CropStep currentCropStep = userProgress.getCropStep();
        model.addAttribute("currentStep",currentCropStep);
        model.addAttribute("finished",userProgress.isFinished());

        return "growing";
    }

    @GetMapping(path = "delete/{userId}/{cropId}")
    public String deleteProgress(@PathVariable("userId") Long userId, @PathVariable("cropId") Long cropId, Model model){
        Crop crop = this.cropService.getById(cropId);
        this.userProgressService.deleteProgress(userId,crop);
        Farmer farmer = farmerService.getFarmerById(userId);
        List<UserProgress> userProgresses = userProgressService.getUserAllProgress(userId);
        System.out.println("UPA " + userProgresses);
        model.addAttribute("user",farmer);
        model.addAttribute("crops",cropService.getAllUserCrops(userId));
        model.addAttribute("ongoing",userProgresses);
        return "farmer";
    }





}
