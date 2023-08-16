package com.clab.securecoding.type.entity;

import com.clab.securecoding.type.etc.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalAdoptionBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    private String species;

    private String name;

    private String color;

    private String gender;

    private Long age;

    private Double weight;

    private String vaccine;

    private Boolean isNeutered;

    private String birthDay;

    private String favoriteSnack;

    private String reasonForAdoption;

    private String areasAvailable;

    private Integer price;

    private Double leadership;

    private Double independence;

    private Double initiative;

    private Double positivity;

    private Double adaptability;

    private String recommendation;

    private String think;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user")
    private User writer;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
