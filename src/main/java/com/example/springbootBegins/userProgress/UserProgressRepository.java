package com.example.springbootBegins.userProgress;

import com.example.springbootBegins.crop.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress,Long> {

    List<UserProgress> findUserProgressByFarmer_IdAndCropStep_Crop(Long farmerId, Crop crop);
    List<UserProgress> findUserProgressByFarmer_Id(Long farmerId);
}
