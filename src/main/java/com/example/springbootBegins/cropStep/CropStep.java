package com.example.springbootBegins.cropStep;

import com.example.springbootBegins.crop.Crop;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class CropStep {

    @Id
    @SequenceGenerator(
            name="cropStep_sequence",
            sequenceName = "cropStep_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cropStep_sequence"
    )
    private Long id;
    private int stepNo;
    private String step;

    // M
    // private Long cropId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "crop_id")
    private Crop crop;

//    @OneToMany(mappedBy = "cropStep")
//    private List<UserProgress> userProgresses;


    public CropStep() {
    }

    public CropStep(Long id, int stepNo, String step,Crop crop) {
        this.id = id;
        this.stepNo = stepNo;
        this.step = step;
        this.crop = crop;
    }

    public CropStep(int stepNo, String step,Crop crop) {
        this.stepNo = stepNo;
        this.step = step;
        this.crop = crop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStepNo() {
        return stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    @Override
    public String toString() {
        return "CropStep{" +
                "id=" + id +
                ", stepNo=" + stepNo +
                ", step='" + step + '\'' +
                ", crop=" + crop +
                '}';
    }
}
