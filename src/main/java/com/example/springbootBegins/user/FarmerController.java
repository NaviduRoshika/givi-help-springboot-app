package com.example.springbootBegins.user;

import com.example.springbootBegins.crop.CropService;
import com.example.springbootBegins.cropStep.CropStepService;
import com.example.springbootBegins.userProgress.UserProgress;
import com.example.springbootBegins.userProgress.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = "farmer")
public class FarmerController {

    private final FarmerService farmerService;
    private final CropService cropService;
    private  final  CropStepService cropStepService;
    private final UserProgressService userProgressService;

    @Autowired
    public FarmerController(FarmerService farmerService,
                            CropService cropService,
                            CropStepService cropStepService,
                            UserProgressService userProgressService) {
        this.farmerService = farmerService;
        this.cropService = cropService;
        this.cropStepService = cropStepService;
        this.userProgressService = userProgressService;
    }


    @GetMapping(path = "{userId}")
    public String home(@PathVariable("userId") Long userId,Model model){
        Farmer farmer = farmerService.getFarmerById(userId);
        List<UserProgress> userProgresses = userProgressService.getUserAllProgress(userId);
        model.addAttribute("user",farmer);
        model.addAttribute("crops",cropService.getAllUserCrops(userId));
        model.addAttribute("ongoing",userProgresses);
        return "farmer";
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam Map<String, String> body, Model model){
        String email = body.get("email");
        String password = body.get("password");
        Long result =  farmerService.login(email,password);
        if (result > 0){
            Farmer farmer = farmerService.getFarmerById(result);
            List<UserProgress> userProgresses = userProgressService.getUserAllProgress(result);
            model.addAttribute("user",farmer);
            model.addAttribute("crops",cropService.getAllUserCrops(result));
            model.addAttribute("ongoing",userProgresses);
            return "farmer";
        }else {
            return "login";
        }
    }

    @GetMapping(path = "/logout")
    public String logout(){
            return "login";
    }

    @GetMapping(path = "/register")
    public String register(){
        return "register";
    }

    @PostMapping
    public String registerUser(@RequestParam Map<String, String> body,Model model){

        String email = body.get("email");
        String password = body.get("password");
        String name = body.get("name");
        Farmer farmer = new Farmer(name,email,password);
        Optional<Farmer> newFarmer = farmerService.addNewUser(farmer);
        if (newFarmer.isPresent()){
            Farmer f = newFarmer.get();
            List<UserProgress> userProgresses = userProgressService.getUserAllProgress(f.getId());
            model.addAttribute("user",f);
            model.addAttribute("crops",cropService.getAllUserCrops(f.getId()));
            model.addAttribute("ongoing",userProgresses);
            return "farmer";
        }else {
            return "register";
        }
    }


    @GetMapping(path = "{userId}/grow/{cropId}")
    public String startGrowCrop(@PathVariable("userId") Long userId,@PathVariable("cropId") Long cropId, Model model){
        model.addAttribute("crop",cropService.getById(cropId));
        model.addAttribute("steps",cropStepService.getCropSteps(cropId));
        Farmer farmer = farmerService.getFarmerById(userId);
        model.addAttribute("user",farmer);
        return "cropSteps";
    }


}
