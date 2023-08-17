package com.clab.securecoding.type.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String timeWithPets;

    private String environment;

    private String numberOfFamily;

    private Boolean hasAllergy;

    private String hospitalExpensesForPets;

    private String numberOfWalks;

    private Boolean hasAlreadyPet;

    private Boolean hasExperienceRaisingPets;

    private String sizeOfHouse;

    private String job;

    private String age;

}
