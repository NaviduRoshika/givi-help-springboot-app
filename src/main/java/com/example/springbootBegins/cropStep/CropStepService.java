package com.example.springbootBegins.cropStep;

import com.example.springbootBegins.crop.Crop;
import com.example.springbootBegins.crop.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public class CropStepService {

    private final CropStepRepository cropStepRepository;

    @Autowired
    public CropStepService(CropStepRepository cropStepRepository) {
        this.cropStepRepository = cropStepRepository;
    }

    public List<CropStep> getCropSteps(Long cropId){
        return cropStepRepository.findCropStepByCrop_Id(cropId);
    }

    public CropStep getNextCropStep(Long cropId, int currentStepNo) {
        List<CropStep> cropSteps = cropStepRepository.findCropStepByCrop_Id(cropId);
        CropStep currentStep = null;
        CropStep nextStep = null;
        for (CropStep cropStep : cropSteps){
               if (cropStep.getStepNo() == (currentStepNo+1)){
                   nextStep = cropStep;
               }
            if (cropStep.getStepNo() == currentStepNo){
                currentStep = cropStep;
            }
        }
        if (nextStep != null){
            return nextStep;
        }else{
            return currentStep;
        }
    }
}
