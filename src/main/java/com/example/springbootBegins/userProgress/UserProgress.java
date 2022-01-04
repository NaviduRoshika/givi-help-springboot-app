package com.example.springbootBegins.userProgress;

import com.example.springbootBegins.crop.Crop;
import com.example.springbootBegins.cropStep.CropStep;
import com.example.springbootBegins.user.Farmer;

import javax.persistence.*;

@Entity
@Table
public class UserProgress {

    @Id
    @SequenceGenerator(
            name="userprogress_sequence",
            sequenceName = "userprogress_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userprogress_sequence"
    )
    private Long id;
    private boolean isFinished;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cropstep_id")
    private CropStep cropStep;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    public UserProgress() {
    }

    public UserProgress(Long id,Farmer farmer,CropStep cropStep) {
        this.id = id;
        this.isFinished = false;
        this.farmer = farmer;
        this.cropStep = cropStep;

    }

    public UserProgress(Farmer farmer,CropStep cropStep) {
        this.isFinished = false;
        this.farmer = farmer;
        this.cropStep = cropStep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public CropStep getCropStep() {
        return cropStep;
    }

    public void setCropStep(CropStep cropStep) {
        this.cropStep = cropStep;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    @Override
    public String toString() {
        return "UserProgress{" +
                "id=" + id +
                ", isFinished=" + isFinished +
                ", cropStep=" + cropStep +
                ", farmer=" + farmer +
                '}';
    }
}
