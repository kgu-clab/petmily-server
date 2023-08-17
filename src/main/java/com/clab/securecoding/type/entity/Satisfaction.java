package com.clab.securecoding.type.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Satisfaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "animal_adoption_board")
    private AnimalAdoptionBoard animalAdoptionBoard;

    private String adoptionProcessSatisfaction;

    private String mostSatisfyingThing;

    private String guideContentSatisfaction;

    private String secondhandTransactionSatisfaction;

    private String intentionToUse;

    private String intentionToRecommendation;

    private String suggestionsForImprovement;
}
