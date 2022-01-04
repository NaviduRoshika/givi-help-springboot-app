package com.example.springbootBegins.cropStep;

import com.example.springbootBegins.crop.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "cropStep")
public class CropStepController {

    private final CropStepService cropStepService;

    @Autowired
    public CropStepController(CropStepService cropStepService) {
        this.cropStepService = cropStepService;
    }




}
