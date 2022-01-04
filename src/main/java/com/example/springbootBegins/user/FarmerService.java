package com.example.springbootBegins.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component
@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    @Autowired
    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Optional<Farmer> addNewUser(Farmer newFarmer) {
        Optional<Farmer> userByEmail = farmerRepository.findUserByEmail(newFarmer.getEmail());
        if (userByEmail.isPresent()){
            return null;
        }
        farmerRepository.save(newFarmer);
        return this.farmerRepository.findUserByEmail(newFarmer.getEmail());
    }


    public Long login(String email, String password) {
        Optional<Farmer> farmer = farmerRepository.findUserByEmail(email);
        if (farmer.isPresent()){
            boolean isValid = password.equals(farmer.get().getPassword());
            if (isValid){
                return farmer.get().getId();
            }
            return -1L;
        }
        return -1L;
    }

    public Farmer getFarmerById(Long result) {
        return farmerRepository.getById(result);
    }
}
