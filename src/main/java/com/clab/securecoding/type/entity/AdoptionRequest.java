package com.clab.securecoding.type.entity;

import com.clab.securecoding.type.etc.RequestState;
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
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private AnimalAdoptionBoard animalAdoptionBoard;

    @Enumerated(EnumType.STRING)
    private RequestState requestState;

}
