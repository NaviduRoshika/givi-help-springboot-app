package com.example.springbootBegins.crop;

import com.example.springbootBegins.cropStep.CropStep;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class Crop {

    @Id
    @SequenceGenerator(
            name="crop_sequence",
            sequenceName = "crop_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "crop_sequence"
    )
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "crop")
    private List<CropStep> cropSteps;


    public Crop() {
    }

    public Crop(Long id, String name, String description ) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Crop(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
