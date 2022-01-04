package com.example.springbootBegins.cropStep;

import com.example.springbootBegins.crop.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropStepRepository extends JpaRepository<CropStep,Long> {

    List<CropStep> findCropStepByCrop_Id(Long id);
}
