package com.example.springbootBegins.crop;

import com.example.springbootBegins.user.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<Crop,Long> {
    Optional<Crop> findCropByName(String name);
}
