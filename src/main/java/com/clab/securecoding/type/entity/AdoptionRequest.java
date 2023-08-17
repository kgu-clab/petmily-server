package com.clab.securecoding.type.entity;

import com.clab.securecoding.type.etc.RequestState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    private List<Contract> contracts;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private AnimalAdoptionBoard animalAdoptionBoard;

    @Enumerated(EnumType.STRING)
    private RequestState requestState;

}
